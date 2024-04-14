package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.UnionContribution;
import com.github.faening.eng_soft_fp_api.data.repository.UnionContributionRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.union_contribution.UnionContributionRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.union_contribution.UnionContributionResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.union_contribution.UnionContributionRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.union_contribution.UnionContributionResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class UnionContributionService extends AbstractService<UnionContributionRequestDTO, UnionContributionResponseDTO> {
    private final UnionContributionRepository repository;
    private final UnionContributionRequestMapper requestMapper;
    private final UnionContributionResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String UC_EMPLOYEE_ID_VALIDATION_MESSAGE = "unionContributionService.validation.employeeId";
    private static final String UC_RELEASE_YEAR_VALIDATION_MESSAGE = "unionContributionService.validation.releaseYear";
    private static final String UC_OPTED_OUT_VALIDATION_MESSAGE = "unionContributionService.validation.optedOut";

    @Autowired
    public UnionContributionService(
        UnionContributionRepository repository,
        UnionContributionRequestMapper requestMapper,
        UnionContributionResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<UnionContributionResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(unionContribution -> responseMapper.toDTO(unionContribution, UnionContributionResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public UnionContributionResponseDTO getById(Integer id) {
        return responseMapper.toDTO(searchUnionContributionEntityById(id), UnionContributionResponseDTO.class);
    }

    public UnionContribution getEntityById(Integer id) {
        return searchUnionContributionEntityById(id);
    }

    public UnionContributionResponseDTO getByEmployeeIdAndReleaseYear(Integer employeeId, Integer releaseYear) {
        Employee employee = employeeService.getEntityById(employeeId);
        return responseMapper.toDTO(repository.findByEmployeeAndReleaseYear(employee, releaseYear), UnionContributionResponseDTO.class);
    }

    @Override
    public UnionContributionResponseDTO create(UnionContributionRequestDTO request) {
        validate(request);
        UnionContribution unionContribution = requestMapper.toEntity(request, UnionContribution.class);
        UnionContribution savedUnionContribution = repository.save(unionContribution);
        return responseMapper.toDTO(savedUnionContribution, UnionContributionResponseDTO.class);
    }

    @Override
    public UnionContributionResponseDTO update(Integer id, UnionContributionRequestDTO request) {
        validate(id);
        validate(request);
        UnionContribution unionContribution = searchUnionContributionEntityById(id);
        requestMapper.updateSourceFromDestination(unionContribution, request);
        UnionContribution updatedUnionContribution = repository.save(unionContribution);
        return responseMapper.toDTO(updatedUnionContribution, UnionContributionResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private UnionContribution searchUnionContributionEntityById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE));
    }

    @Override
    protected void validate(UnionContributionRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(UC_EMPLOYEE_ID_VALIDATION_MESSAGE);
        if (request.getReleaseYear() == null) throw new IllegalArgumentException(UC_RELEASE_YEAR_VALIDATION_MESSAGE);
        if (request.getOptedOut() == null) throw new IllegalArgumentException(UC_OPTED_OUT_VALIDATION_MESSAGE);
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
    }
}