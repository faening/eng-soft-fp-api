package com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;

@SuppressWarnings("unused")
public class LegalChargeInstallmentRequestDTO implements Serializable {
    private Integer installmentNumber;
    private Double installmentValue;
    private Month discountMonth;
    private PaymentStatus paymentStatus;

    public LegalChargeInstallmentRequestDTO() {
    }

    public LegalChargeInstallmentRequestDTO(
        Integer installmentNumber,
        Double installmentValue,
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

    public Double getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(Double installmentValue) {
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
        return "LegalChargeInstallmentRequestDTO{" +
            "installmentNumber=" + installmentNumber +
            ", installmentValue=" + installmentValue +
            ", discountMonth=" + discountMonth +
            ", paymentStatus='" + paymentStatus + '\'' +
            '}';
    }
}