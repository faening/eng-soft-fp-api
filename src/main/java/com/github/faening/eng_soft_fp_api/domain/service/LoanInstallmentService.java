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
    private final LoanInstallmentRepository loanInstallmentRepository;
    private final LoanInstallmentRequestMapper loanInstallmentRequestMapper;
    private final LoanInstallmentResponseMapper loanInstallmentResponseMapper;
    private final LoanService loanService;

    @Autowired
    public LoanInstallmentService(
        LoanInstallmentRepository loanInstallmentRepository,
        LoanInstallmentRequestMapper loanInstallmentRequestMapper,
        LoanInstallmentResponseMapper loanInstallmentResponseMapper,
        LoanService loanService
    ) {
        this.loanInstallmentRepository = loanInstallmentRepository;
        this.loanInstallmentRequestMapper = loanInstallmentRequestMapper;
        this.loanInstallmentResponseMapper = loanInstallmentResponseMapper;
        this.loanService = loanService;
    }

    @Override
    public List<LoanInstallmentResponseDTO> getAll() {
        return loanInstallmentRepository
            .findAll()
            .stream()
            .map(loanInstallment -> loanInstallmentResponseMapper.toDTO(loanInstallment, LoanInstallmentResponseDTO.class))
            .toList();
    }

    @Override
    public LoanInstallmentResponseDTO getById(Integer id) {
        validateId(id);
        return loanInstallmentResponseMapper.toDTO(searchLoanInstallmentById(id), LoanInstallmentResponseDTO.class);
    }

    @Override
    public LoanInstallmentResponseDTO create(LoanInstallmentRequestDTO request) {
        validateLoanInstallmentRequestDTO(request);
        LoanInstallment loanInstallment = loanInstallmentRequestMapper.toEntity(request, LoanInstallment.class);
        LoanInstallment savedLoanInstallment = loanInstallmentRepository.save(loanInstallment);
        return loanInstallmentResponseMapper.toDTO(savedLoanInstallment, LoanInstallmentResponseDTO.class);
    }

    @Override
    public LoanInstallmentResponseDTO update(Integer id, LoanInstallmentRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        loanInstallmentRepository.delete(searchLoanInstallmentById(id));
    }

    public LoanInstallment searchLoanInstallmentById(Integer id) {
        return loanInstallmentRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Nenhuma parcela de empréstimo encontrada com o id: " + id)
        );
    }

    public List<LoanInstallmentResponseDTO> searchLoanInstallmentByLoanId(Integer loanId) {
        Loan loan = loanService.searchLoanById(loanId);
        return loanInstallmentRepository
            .findByLoan(loan)
            .stream()
            .map(loanInstallment -> loanInstallmentResponseMapper.toDTO(loanInstallment, LoanInstallmentResponseDTO.class))
            .toList();
    }

    public List<LoanInstallment> searchLoanInstallmentEntityByLoanId(Integer loanId) {
        Loan loan = loanService.searchLoanById(loanId);
        return loanInstallmentRepository
            .findByLoan(loan)
            .stream()
            .toList();
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(getLocalizedMessage("loanInstallmentService.validation.loanInstallmentId"));
        }
    }

    private void validateLoanInstallmentRequestDTO(LoanInstallmentRequestDTO request) {
        if (request.getInstallmentNumber() == null)  throw new IllegalArgumentException(getLocalizedMessage("loanInstallmentService.validation.installmentNumber"));
        if (request.getInstallmentValue() == null) throw new IllegalArgumentException(getLocalizedMessage("loanInstallmentService.validation.installmentValue"));
        if (request.getDiscountMonth() == null) throw new IllegalArgumentException(getLocalizedMessage("loanInstallmentService.validation.discountMonth"));
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
    }
}