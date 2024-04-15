package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.EmployeeDependent;
import com.github.faening.eng_soft_fp_api.data.repository.EmployeeDependentRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee_dependent.EmployeeDependentRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee_dependent.EmployeeDependentResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class EmployeeDependentService extends AbstractService<EmployeeDependentRequestDTO, EmployeeDependentResponseDTO>{
    private final EmployeeDependentRepository repository;
    private final EmployeeDependentRequestMapper requestMapper;
    private final EmployeeDependentResponseMapper responseMapper;

    private static final String VALIDATION_MESSAGE_EMPLOYEE_ID = "employeeDependentService.validation.employeeId";
    private static final String VALIDATION_MESSAGE_NAME = "employeeDependentService.validation.name";
    private static final String VALIDATION_MESSAGE_RG = "employeeDependentService.validation.rg";
    private static final String VALIDATION_MESSAGE_CPF = "employeeDependentService.validation.cpf";
    private static final String VALIDATION_MESSAGE_BIRTH_DATE = "employeeDependentService.validation.birthDate";
    private static final String VALIDATION_MESSAGE_GENDER = "employeeDependentService.validation.gender";

    @Autowired
    public EmployeeDependentService(
        EmployeeDependentRepository repository,
        EmployeeDependentRequestMapper requestMapper,
        EmployeeDependentResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<EmployeeDependentResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(employeeDependent -> responseMapper.toDTO(employeeDependent, EmployeeDependentResponseDTO.class))
            .toList();
    }

    @Override
    public EmployeeDependentResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchEmployeeDependentEntityById(id), EmployeeDependentResponseDTO.class);
    }

    public EmployeeDependent getEntityById(Integer id) {
        validate(id);
        return searchEmployeeDependentEntityById(id);
    }

    @Override
    public EmployeeDependentResponseDTO create(EmployeeDependentRequestDTO request) {
        validate(request);
        EmployeeDependent employeeDependent = requestMapper.toEntity(request, EmployeeDependent.class);
        EmployeeDependent savedEmployeeDependent = repository.save(employeeDependent);
        return responseMapper.toDTO(savedEmployeeDependent, EmployeeDependentResponseDTO.class);
    }

    @Override
    public EmployeeDependentResponseDTO update(Integer id, EmployeeDependentRequestDTO request) {
        validate(id);
        validate(request);
        EmployeeDependent employeeDependent = searchEmployeeDependentEntityById(id);
        requestMapper.updateSourceFromDestination(employeeDependent, request);
        return responseMapper.toDTO(repository.save(employeeDependent), EmployeeDependentResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private EmployeeDependent searchEmployeeDependentEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(EmployeeDependentRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_EMPLOYEE_ID));
        if (request.getName() == null) throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_NAME));
        if (request.getRg() == null) throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_RG));
        if (request.getCpf() == null) throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_CPF));
        if (request.getBirthDate() == null) throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_BIRTH_DATE));
        if (request.getGender() == null) throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_GENDER));
        if (request.getSpecialNeeds() == null) request.setSpecialNeeds(false);
        if (request.getFamilyAllowance() == null) request.setSpecialNeeds(false);
        if (request.getDaycareAllowance() == null) request.setSpecialNeeds(false);
    }
}