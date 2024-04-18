package com.github.faening.eng_soft_fp_api.domain.model.loan;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentRequestDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class LoanRequestDTO implements Serializable {
    private Integer employeeId;
    private BigDecimal loanAmountValue;
    private Integer installmentQuantity;
    private LocalDate requestDate;
    private LocalDate approvalDate;
    private LocalDate companyPaymentDate;
    private PaymentStatus companyPaymentStatus;
    private PaymentStatus employeePaymentStatus;
    private List<LoanInstallmentRequestDTO> installments;

    public LoanRequestDTO() {
    }

    public LoanRequestDTO(
        Integer employeeId,
        BigDecimal loanAmountValue,
        Integer installmentQuantity,
        LocalDate requestDate,
        LocalDate approvalDate,
        LocalDate companyPaymentDate,
        PaymentStatus companyPaymentStatus,
        PaymentStatus employeePaymentStatus,
        List<LoanInstallmentRequestDTO> installments
    ) {
        this.employeeId = employeeId;
        this.loanAmountValue = loanAmountValue;
        this.installmentQuantity = installmentQuantity;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.companyPaymentDate = companyPaymentDate;
        this.employeePaymentStatus = employeePaymentStatus;
        this.companyPaymentStatus = companyPaymentStatus;
        this.installments = installments;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getLoanAmountValue() {
        return loanAmountValue;
    }

    public void setLoanAmountValue(BigDecimal loanAmountValue) {
        this.loanAmountValue = loanAmountValue;
    }

    public Integer getInstallmentQuantity() {
        return installmentQuantity;
    }

    public void setInstallmentQuantity(Integer installmentQuantity) {
        this.installmentQuantity = installmentQuantity;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getCompanyPaymentDate() {
        return companyPaymentDate;
    }

    public void setCompanyPaymentDate(LocalDate companyPaymentDate) {
        this.companyPaymentDate = companyPaymentDate;
    }

    public PaymentStatus getCompanyPaymentStatus() {
        return companyPaymentStatus;
    }

    public void setCompanyPaymentStatus(PaymentStatus companyPaymentStatus) {
        this.companyPaymentStatus = companyPaymentStatus;
    }

    public PaymentStatus getEmployeePaymentStatus() {
        return employeePaymentStatus;
    }

    public void setEmployeePaymentStatus(PaymentStatus employeePaymentStatus) {
        this.employeePaymentStatus = employeePaymentStatus;
    }

    public List<LoanInstallmentRequestDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LoanInstallmentRequestDTO> installments) {
        this.installments = installments;
    }

    @Override
    public String toString() {
        return "LoanRequestDTO{" +
            ", employeeId=" + employeeId +
            ", loanAmountValue=" + loanAmountValue +
            ", installmentQuantity=" + installmentQuantity +
            ", requestDate=" + requestDate +
            ", approvalDate=" + approvalDate +
            ", companyPaymentDate=" + companyPaymentDate +
            ", employeePaymentDate=" + employeePaymentStatus +
            ", paymentStatus=" + companyPaymentStatus +
            ", installments=" + installments +
            '}';
    }
}