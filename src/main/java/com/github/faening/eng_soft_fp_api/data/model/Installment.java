package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Embeddable
public class Installment implements Serializable {
    @Column(name = "installment_number", nullable = false)
    private Integer installmentNumber;

    @Column(name = "installment_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal installmentValue;

    @Column(name = "discount_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month discountMonth;

    @Column(name = "payment_status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Installment() {
    }

    public Installment(
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
}