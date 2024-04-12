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

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LoanService extends AbstractService<LoanRequestDTO, LoanResponseDTO> {
    private final LoanRepository loanRepository;
    private final LoanRequestMapper loanRequestMapper;
    private final LoanResponseMapper loanResponseMapper;
    private final EmployeeService employeeService;

    @Autowired
    public LoanService(
        LoanRepository loanRepository,
        LoanRequestMapper loanRequestMapper,
        LoanResponseMapper loanResponseMapper,
        EmployeeService employeeService
    ) {
        this.loanRepository = loanRepository;
        this.loanRequestMapper = loanRequestMapper;
        this.loanResponseMapper = loanResponseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<LoanResponseDTO> getAll() {
        return loanRepository
            .findAll()
            .stream()
            .map(loan -> loanResponseMapper.toDTO(loan, LoanResponseDTO.class))
            .toList();
    }

    @Override
    public LoanResponseDTO getById(Integer id) {
        validadeId(id);
        return loanResponseMapper.toDTO(searchLoanById(id), LoanResponseDTO.class);
    }

    @Override
    public LoanResponseDTO create(LoanRequestDTO request) {
        validateLoanRequestDTO(request);
        request.getInstallments().forEach(installment -> installment.setPaymentStatus(PaymentStatus.PENDING));
        Loan loan = loanRequestMapper.toEntity(request, Loan.class);
        loan.getInstallments().forEach(installment -> {installment.setLoan(loan);});
        Loan savedLoan = loanRepository.save(loan);
        return loanResponseMapper.toDTO(savedLoan, LoanResponseDTO.class);
    }

    @Override
    public LoanResponseDTO update(Integer id, LoanRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validadeId(id);
        loanRepository.delete(searchLoanById(id));
    }

    public Loan searchLoanById(Integer id) {
        return loanRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Nenhum empréstimo encontrado com o id: " + id)
        );
    }

    /**
     * Busca empréstimos por id do funcionário e especificações.
     *
     * @param employeeId id do funcionário
     * @param requestDate data de solicitação
     * @param approvalDate data de aprovação
     * @param companyPaymentDate data de pagamento da empresa
     * @param paymentStatus status do pagamento
     * @return lista de empréstimos
     */
    public List<LoanResponseDTO> searchLoanByEmployeeIdAndSpecs(
        Integer employeeId,
        LocalDate requestDate,
        LocalDate approvalDate,
        LocalDate companyPaymentDate,
        PaymentStatus paymentStatus
    ) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        LoanSpecification spec = new LoanSpecification(employee, requestDate, approvalDate, companyPaymentDate, paymentStatus);
        return loanRepository
            .findAll(spec)
            .stream()
            .map(loan -> loanResponseMapper.toDTO(loan, LoanResponseDTO.class))
            .toList();
    }

    private void validadeId(Integer id) {
        if (id == null || id <= 0) throw new IllegalArgumentException(getLocalizedMessage("loanService.validation.loanId"));
    }

    private void validateLoanRequestDTO(LoanRequestDTO request) {
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage("loanService.validation.employeeId"));
        if (request.getLoanAmountValue() == null) throw new IllegalArgumentException(getLocalizedMessage("loanService.validation.loanAmountValue"));
        if (request.getInstallmentQuantity() == null) throw new IllegalArgumentException(getLocalizedMessage("loanService.validation.installmentQuantity"));
        if (request.getRequestDate() == null) throw new IllegalArgumentException(getLocalizedMessage("loanService.validation.requestDate"));
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
    }
}