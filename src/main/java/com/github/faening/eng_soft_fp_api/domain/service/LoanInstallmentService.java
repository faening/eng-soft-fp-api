package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Loan;
import com.github.faening.eng_soft_fp_api.data.model.LoanInstallment;
import com.github.faening.eng_soft_fp_api.data.repository.LoanInstallmentRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.loan_installment.LoanInstallmentRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.loan_installment.LoanInstallmentResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LoanInstallmentService extends AbstractService<LoanInstallmentRequestDTO, LoanInstallmentResponseDTO> {
    private final LoanInstallmentRepository repository;
    private final LoanInstallmentRequestMapper requestMapper;
    private final LoanInstallmentResponseMapper responseMapper;
    private final LoanService loanService;

    private static final String LOAN_INSTALLMENT_NUMBER_VALIDATION_MESSAGE = "loanInstallmentService.validation.installmentNumber";
    private static final String LOAN_INSTALLMENT_VALUE_VALIDATION_MESSAGE = "loanInstallmentService.validation.installmentValue";
    private static final String LOAN_INSTALLMENT_MONTH_VALIDATION_MESSAGE = "loanInstallmentService.validation.discountMonth";

    @Autowired
    public LoanInstallmentService(
        LoanInstallmentRepository repository,
        LoanInstallmentRequestMapper requestMapper,
        LoanInstallmentResponseMapper responseMapper,
        LoanService loanService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.loanService = loanService;
    }

    @Override
    public List<LoanInstallmentResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(loanInstallment -> responseMapper.toDTO(loanInstallment, LoanInstallmentResponseDTO.class))
            .toList();
    }

    @Override
    public LoanInstallmentResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchLoanInstallmentById(id), LoanInstallmentResponseDTO.class);
    }

    public List<LoanInstallmentResponseDTO> getByLoanId(Integer loanId) {
        return searchLoanInstallmentByLoanId(loanId);
    }

    public List<LoanInstallment> getEntityByLoanId(Integer loanId) {
        return searchLoanInstallmentEntityByLoanId(loanId);
    }

    @Override
    public LoanInstallmentResponseDTO create(LoanInstallmentRequestDTO request) {
        validate(request);
        LoanInstallment loanInstallment = requestMapper.toEntity(request, LoanInstallment.class);
        LoanInstallment savedLoanInstallment = repository.save(loanInstallment);
        return responseMapper.toDTO(savedLoanInstallment, LoanInstallmentResponseDTO.class);
    }

    @Override
    public LoanInstallmentResponseDTO update(Integer id, LoanInstallmentRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.delete(searchLoanInstallmentById(id));
    }

    private LoanInstallment searchLoanInstallmentById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException(ID_VALIDATION_MESSAGE)
        );
    }

    private List<LoanInstallmentResponseDTO> searchLoanInstallmentByLoanId(Integer loanId) {
        Loan loan = loanService.getEntityById(loanId);
        return repository
            .findByLoan(loan)
            .stream()
            .map(loanInstallment -> responseMapper.toDTO(loanInstallment, LoanInstallmentResponseDTO.class))
            .toList();
    }

    private List<LoanInstallment> searchLoanInstallmentEntityByLoanId(Integer loanId) {
        Loan loan = loanService.getEntityById(loanId);
        return repository
            .findByLoan(loan)
            .stream()
            .toList();
    }

    @Override
    protected void validate(LoanInstallmentRequestDTO request) {
        if (request.getInstallmentNumber() == null)  throw new IllegalArgumentException(getLocalizedMessage(LOAN_INSTALLMENT_NUMBER_VALIDATION_MESSAGE));
        if (request.getInstallmentValue() == null) throw new IllegalArgumentException(getLocalizedMessage(LOAN_INSTALLMENT_VALUE_VALIDATION_MESSAGE));
        if (request.getDiscountMonth() == null) throw new IllegalArgumentException(getLocalizedMessage(LOAN_INSTALLMENT_MONTH_VALIDATION_MESSAGE));
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
    }
}