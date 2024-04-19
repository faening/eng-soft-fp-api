package com.github.faening.eng_soft_fp_api.domain.model.loan_installment;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class LoanInstallmentRequestDTO implements Serializable {
    private Integer installmentNumber;
    private BigDecimal installmentValue;
    private Month discountMonth;
    private PaymentStatus paymentStatus;

    public LoanInstallmentRequestDTO() {
    }

    public LoanInstallmentRequestDTO(
        Integer installmentNumber,
        BigDecimal installmentValue,
        Month discountMonth,
        PaymentStatus paymentStatus
    ) {
        this.installmentNumber = installmentNumber;
        this.installmentValue = installmentValue;
        this.discountMonth = discountMonth;
        this.paymentStatus = paymentStatus;
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

    @Override
    public String toString() {
        return "LoanInstallmentRequestDTO{" +
            ", installmentNumber=" + installmentNumber +
            ", installmentValue=" + installmentValue +
            ", discountMonth=" + discountMonth +
            ", paymentStatus=" + paymentStatus +
            '}';
    }
}