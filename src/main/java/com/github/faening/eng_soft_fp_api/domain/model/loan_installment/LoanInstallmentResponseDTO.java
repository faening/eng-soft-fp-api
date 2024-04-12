package com.github.faening.eng_soft_fp_api.domain.model.loan_installment;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class LoanInstallmentResponseDTO implements Serializable {
    private Integer id;
    private Integer loanId;
    private Integer installmentNumber;
    private BigDecimal installmentValue;
    private Month discountMonth;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LoanInstallmentResponseDTO() {
    }

    public LoanInstallmentResponseDTO(
        Integer id,
        Integer loanId,
        Integer installmentNumber,
        BigDecimal installmentValue,
        Month discountMonth,
        PaymentStatus paymentStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.loanId = loanId;
        this.installmentNumber = installmentNumber;
        this.installmentValue = installmentValue;
        this.discountMonth = discountMonth;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public BigDecimal getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(BigDecimal installmentValue) {
        this.installmentValue = installmentValue;
    }

    public Month getDiscountMonth() {
        return discountMonth;
    }

    public void setDiscountMonth(Month discountMonth) {
        this.discountMonth = discountMonth;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        return "LoanInstallmentResponseDTO{" +
            "id=" + id +
            ", loanId=" + loanId +
            ", installmentNumber=" + installmentNumber +
            ", installmentValue=" + installmentValue +
            ", discountMonth=" + discountMonth +
            ", paymentStatus=" + paymentStatus +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}