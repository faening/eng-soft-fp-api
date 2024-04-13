package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Loan;
import com.github.faening.eng_soft_fp_api.data.repository.LoanRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.loan.LoanRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.loan.LoanResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.specification.LoanSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class LoanService extends AbstractService<LoanRequestDTO, LoanResponseDTO> {
    private final LoanRepository repository;
    private final LoanRequestMapper requestMapper;
    private final LoanResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String LOAN_EMPLOYEE_ID_VALIDATION_MESSAGE = "loanService.validation.employeeId";
    private static final String LOAN_AMOUNT_VALUE_VALIDATION_MESSAGE = "loanService.validation.loanAmountValue";
    private static final String LOAN_INSTALLMENT_QUANTITY_VALIDATION_MESSAGE = "loanService.validation.installmentQuantity";
    private static final String LOAN_REQUEST_DATE_VALIDATION_MESSAGE = "loanService.validation.requestDate";

    @Autowired
    public LoanService(
        LoanRepository repository,
        LoanRequestMapper requestMapper,
        LoanResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<LoanResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(loan -> responseMapper.toDTO(loan, LoanResponseDTO.class))
            .toList();
    }

    @Override
    public LoanResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchLoanById(id), LoanResponseDTO.class);
    }

    public Loan getEntityById(Integer id) {
        validate(id);
        return searchLoanById(id);
    }

    public List<LoanResponseDTO> searchLoanByEmployeeIdAndSpecs(
        Integer employeeId,
        LocalDate requestDate,
        LocalDate approvalDate,
        LocalDate companyPaymentDate,
        PaymentStatus paymentStatus
    ) {
        Employee employee = employeeService.getEmployeeEntityById(employeeId);
        LoanSpecification spec = new LoanSpecification(employee, requestDate, approvalDate, companyPaymentDate, paymentStatus);
        return repository
            .findAll(spec)
            .stream()
            .map(loan -> responseMapper.toDTO(loan, LoanResponseDTO.class))
            .toList();
    }

    @Override
    public LoanResponseDTO create(LoanRequestDTO request) {
        validate(request);
        request.getInstallments().forEach(installment -> installment.setPaymentStatus(PaymentStatus.PENDING));
        Loan loan = requestMapper.toEntity(request, Loan.class);
        loan.getInstallments().forEach(installment -> {installment.setLoan(loan);});
        Loan savedLoan = repository.save(loan);
        return responseMapper.toDTO(savedLoan, LoanResponseDTO.class);
    }

    @Override
    public LoanResponseDTO update(Integer id, LoanRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.delete(searchLoanById(id));
    }

    private Loan searchLoanById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(LoanRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage(LOAN_EMPLOYEE_ID_VALIDATION_MESSAGE));
        if (request.getLoanAmountValue() == null) throw new IllegalArgumentException(getLocalizedMessage(LOAN_AMOUNT_VALUE_VALIDATION_MESSAGE));
        if (request.getInstallmentQuantity() == null) throw new IllegalArgumentException(getLocalizedMessage(LOAN_INSTALLMENT_QUANTITY_VALIDATION_MESSAGE));
        if (request.getRequestDate() == null) throw new IllegalArgumentException(getLocalizedMessage(LOAN_REQUEST_DATE_VALIDATION_MESSAGE));
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
    }
}