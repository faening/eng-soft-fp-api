package com.github.faening.eng_soft_fp_api.domain.model.loan;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class LoanResponseDTO implements Serializable {
    private Integer id;
    private Integer employeeId;
    private BigDecimal loanAmountValue;
    private Integer installmentQuantity;
    private LocalDate requestDate;
    private LocalDate approvalDate;
    private LocalDate companyPaymentDate;
    private PaymentStatus paymentStatus;
    private List<LoanInstallmentResponseDTO> installments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LoanResponseDTO() {
    }

    public LoanResponseDTO(
        Integer id,
        Integer employeeId,
        BigDecimal loanAmountValue,
        Integer installmentQuantity,
        LocalDate requestDate,
        LocalDate approvalDate,
        LocalDate companyPaymentDate,
        PaymentStatus paymentStatus,
        List<LoanInstallmentResponseDTO> installments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.loanAmountValue = loanAmountValue;
        this.installmentQuantity = installmentQuantity;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.companyPaymentDate = companyPaymentDate;
        this.paymentStatus = paymentStatus;
        this.installments = installments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<LoanInstallmentResponseDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LoanInstallmentResponseDTO> installments) {
        this.installments = installments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "LoanResponseDTO{" +
            "id=" + id +
            ", employeeId=" + employeeId +
            ", loanAmountValue=" + loanAmountValue +
            ", installmentQuantity=" + installmentQuantity +
            ", requestDate=" + requestDate +
            ", approvalDate=" + approvalDate +
            ", companyPaymentDate=" + companyPaymentDate +
            ", paymentStatus=" + paymentStatus +
            ", installments=" + installments +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}