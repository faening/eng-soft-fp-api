package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import com.github.faening.eng_soft_fp_api.data.repository.LegalChargeRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge.LegalChargeRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge.LegalChargeResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge.LegalChargeRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge.LegalChargeResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.specification.LegalChargeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class LegalChargeService extends AbstractService<LegalChargeRequestDTO, LegalChargeResponseDTO> {
    private final LegalChargeRepository repository;
    private final LegalChargeRequestMapper requestMapper;
    private final LegalChargeResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String VALIDATION_MESSAGE_EMPLOYEE_ID = "legalChargeService.validation.employeeId";
    private static final String VALIDATION_MESSAGE_AMOUNT = "legalChargeService.validation.amount";
    private static final String VALIDATION_MESSAGE_PERCENTAGE = "legalChargeService.validation.percentage";
    private static final String VALIDATION_MESSAGE_RELEASE_DATE = "legalChargeService.validation.releaseDate";
    private static final String VALIDATION_MESSAGE_RECURRING = "legalChargeService.validation.recurring";

    @Autowired
    public LegalChargeService(
        LegalChargeRepository repository,
        LegalChargeRequestMapper requestMapper,
        LegalChargeResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<LegalChargeResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(legalCharge -> responseMapper.toDTO(legalCharge, LegalChargeResponseDTO.class))
            .toList();
    }

    @Override
    public LegalChargeResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchLegalChargeById(id), LegalChargeResponseDTO.class);
    }

    public LegalCharge getEntityById(Integer id) {
        return searchLegalChargeById(id);
    }

    public List<LegalChargeResponseDTO> getLegalChargeByEmployeeIdAndSpecs(
        Integer employeeId,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring
    ) {
        return searchLegalChargeByEmployeeIdAndSpecs(employeeId, legalChargeAmount, percentage, releaseDate, isRecurring)
            .stream()
            .map(legalCharge -> responseMapper.toDTO(legalCharge, LegalChargeResponseDTO.class))
            .toList();
    }

    public List<LegalCharge> getLegalChargeEntityByEmployeeIdAndSpecs(
        Integer employeeId,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring
    ) {
        return searchLegalChargeByEmployeeIdAndSpecs(employeeId, legalChargeAmount, percentage, releaseDate, isRecurring);
    }

    @Override
    public LegalChargeResponseDTO create(LegalChargeRequestDTO request) {
        validate(request);
        request.getInstallments().forEach(installment -> installment.setPaymentStatus(PaymentStatus.PENDING));
        LegalCharge legalCharge = requestMapper.toEntity(request, LegalCharge.class);
        legalCharge.getInstallments().forEach(installment -> installment.setLegalCharge(legalCharge));
        LegalCharge savedLegalCharge = repository.save(legalCharge);
        return responseMapper.toDTO(savedLegalCharge, LegalChargeResponseDTO.class);
    }

    @Override
    public LegalChargeResponseDTO update(Integer id, LegalChargeRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.delete(searchLegalChargeById(id));
    }

    private LegalCharge searchLegalChargeById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    private List<LegalCharge> searchLegalChargeByEmployeeIdAndSpecs(
        Integer employeeId,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring
    ) {
        Employee employee = employeeService.getEntityById(employeeId);
        LegalChargeSpecification spec = new LegalChargeSpecification(employee, legalChargeAmount, percentage, releaseDate, isRecurring);
        return repository
            .findAll(spec)
            .stream()
            .toList();
    }

    @Override
    protected void validate(LegalChargeRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_EMPLOYEE_ID));
        if (request.getLegalChargeAmount() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_AMOUNT));
        if (request.getPercentage() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_PERCENTAGE));
        if (request.getReleaseDate() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_RELEASE_DATE));
        if (request.getRecurring() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_RECURRING));
    }
}