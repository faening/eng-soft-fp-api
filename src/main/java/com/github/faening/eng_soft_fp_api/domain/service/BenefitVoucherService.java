package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.BenefitVoucher;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.repository.BenefitVoucherRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.benefit_voucher.BenefitVoucherRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.benefit_voucher.BenefitVoucherResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher.BenefitVoucherRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher.BenefitVoucherResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.specification.BenefitVoucherSpecification;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class BenefitVoucherService extends AbstractService<BenefitVoucherRequestDTO, BenefitVoucherResponseDTO> {
    private final BenefitVoucherRepository repository;
    private final BenefitVoucherRequestMapper requestMapper;
    private final BenefitVoucherResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String VALIDATION_MESSAGE_EMPLOYEE_ID = "benefitVoucherService.validation.employeeId";
    private static final String VALIDATION_MESSAGE_PAID_VALUE = "benefitVoucherService.validation.paidValue";
    private static final String VALIDATION_MESSAGE_RELEASE_DATE = "benefitVoucherService.validation.releaseDate";
    private static final String VALIDATION_MESSAGE_DESCRIPTION = "benefitVoucherService.validation.description";
    private static final String VALIDATION_MESSAGE_BENEFIT_TYPE = "benefitVoucherService.validation.benefitType";
    private static final String VALIDATION_MESSAGE_PAYMENT_DEDUCTIBLE = "benefitVoucherService.validation.paymentDeductible";

    @Autowired
    public BenefitVoucherService(
        BenefitVoucherRepository repository,
        BenefitVoucherRequestMapper requestMapper,
        BenefitVoucherResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<BenefitVoucherResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(benefitVoucher -> responseMapper.toDTO(benefitVoucher, BenefitVoucherResponseDTO.class))
            .toList();
    }

    @Override
    public BenefitVoucherResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchBenefitVoucherEntityById(id), BenefitVoucherResponseDTO.class);
    }

    public BenefitVoucher getEntityById(Integer id) {
        return searchBenefitVoucherEntityById(id);
    }

    public List<BenefitVoucherResponseDTO> getBenefitVouchersByEmployeeAndSpecs(
        Integer employeeId,
        LocalDate releaseDate,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible

    ) {
        return searchBenefitVoucherByEmployeeAndSpecs(employeeId, releaseDate, benefitType, paymentStatus, payrollDeductible)
            .stream()
            .map(benefitVoucher -> responseMapper.toDTO(benefitVoucher, BenefitVoucherResponseDTO.class))
            .toList();
    }

    public List<BenefitVoucher> getBenefitVoucherEntityByEmployeeAndSpecs(
        Integer employeeId,
        LocalDate releaseDate,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible
    ) {
        return searchBenefitVoucherByEmployeeAndSpecs(employeeId, releaseDate, benefitType, paymentStatus, payrollDeductible);
    }

    @Override
    public BenefitVoucherResponseDTO create(BenefitVoucherRequestDTO request) {
        validate(request);
        BenefitVoucher benefitVoucher = requestMapper.toEntity(request, BenefitVoucher.class);
        BenefitVoucher savedBenefitVoucher = repository.save(benefitVoucher);
        return responseMapper.toDTO(savedBenefitVoucher, BenefitVoucherResponseDTO.class);
    }

    @Override
    public BenefitVoucherResponseDTO update(Integer id, BenefitVoucherRequestDTO request) {
        validate(id);
        validate(request);
        BenefitVoucher benefitVoucher = searchBenefitVoucherEntityById(id);
        requestMapper.updateSourceFromDestination(benefitVoucher, request);
        return responseMapper.toDTO(repository.save(benefitVoucher), BenefitVoucherResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private BenefitVoucher searchBenefitVoucherEntityById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE));
    }

    private List<BenefitVoucher> searchBenefitVoucherByEmployeeAndSpecs(
        Integer employeeId,
        LocalDate releaseDate,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible
    ) {
        Employee employee = employeeService.getEntityById(employeeId);
        BenefitVoucherSpecification spec = new BenefitVoucherSpecification(employee, releaseDate, benefitType, paymentStatus, payrollDeductible);
        return repository.findAll(spec);
    }

    @Override
    protected void validate(BenefitVoucherRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null)
            throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_EMPLOYEE_ID));
        if (request.getPaidValue() == null)
            throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_PAID_VALUE));
        if (request.getReleaseDate() == null)
            throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_RELEASE_DATE));
        if (request.getDescription() == null)
            throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_DESCRIPTION));
        if (request.getBenefitType() == null)
            throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_BENEFIT_TYPE));
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
        if (request.getPayrollDeductible() == null)
            throw new ResourceNotFoundException(getLocalizedMessage(VALIDATION_MESSAGE_PAYMENT_DEDUCTIBLE));
    }
}