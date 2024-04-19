package com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class BenefitVoucherResponseDTO implements Serializable {
    private Integer id;
    private Integer employeeId;
    private Double paidValue;
    private LocalDate releaseDate;
    private String description;
    private BenefitVoucherType benefitType;
    private PaymentStatus paymentStatus;
    private Boolean payrollDeductible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BenefitVoucherResponseDTO() {
    }

    public BenefitVoucherResponseDTO(
        Integer id,
        Integer employeeId,
        Double paidValue,
        LocalDate releaseDate,
        String description,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.paidValue = paidValue;
        this.releaseDate = releaseDate;
        this.description = description;
        this.benefitType = benefitType;
        this.paymentStatus = paymentStatus;
        this.payrollDeductible = payrollDeductible;
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

    public Double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(Double paidValue) {
        this.paidValue = paidValue;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BenefitVoucherType getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(BenefitVoucherType benefitType) {
        this.benefitType = benefitType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Boolean getPayrollDeductible() {
        return payrollDeductible;
    }

    public void setPayrollDeductible(Boolean payrollDeductible) {
        this.payrollDeductible = payrollDeductible;
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
        return "BenefitVoucherResponseDTO{" +
            "id=" + id +
            ", employeeId=" + employeeId +
            ", paidValue=" + paidValue +
            ", releaseDate=" + releaseDate +
            ", description='" + description + '\'' +
            ", benefitType=" + benefitType +
            ", paymentStatus=" + paymentStatus +
            ", payrollDeductible=" + payrollDeductible +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}