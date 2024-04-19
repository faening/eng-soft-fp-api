package com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;

import java.io.Serializable;

@SuppressWarnings("unused")
public class LegalChargeInstallmentResponseDTO implements Serializable {
    private Integer id;
    private Integer legalChargeId;
    private Integer installmentNumber;
    private Double installmentValue;
    private Month discountMonth;
    private String paymentStatus;
    private String createdAt;
    private String updatedAt;

    public LegalChargeInstallmentResponseDTO() {
    }

    public LegalChargeInstallmentResponseDTO(
        Integer id,
        Integer legalChargeId,
        Integer installmentNumber,
        Double installmentValue,
        Month discountMonth,
        String paymentStatus,
        String createdAt,
        String updatedAt
    ) {
        this.id = id;
        this.legalChargeId = legalChargeId;
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

    public Integer getLegalChargeId() {
        return legalChargeId;
    }

    public void setLegalChargeId(Integer legalChargeId) {
        this.legalChargeId = legalChargeId;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "LegalChargeInstallmentResponseDTO{" +
            "id=" + id +
            ", legalChargeId=" + legalChargeId +
            ", installmentNumber=" + installmentNumber +
            ", installmentValue=" + installmentValue +
            ", discountMonth=" + discountMonth +
            ", paymentStatus='" + paymentStatus + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", updatedAt='" + updatedAt + '\'' +
            '}';
    }
}