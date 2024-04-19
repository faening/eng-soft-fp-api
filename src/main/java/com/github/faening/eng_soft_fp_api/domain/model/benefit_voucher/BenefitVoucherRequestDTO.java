package com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class BenefitVoucherRequestDTO implements Serializable {
    private Integer employeeId;
    private Double paidValue;
    private LocalDate releaseDate;
    private String description;
    private BenefitVoucherType benefitType;
    private PaymentStatus paymentStatus;
    private Boolean payrollDeductible;

    public BenefitVoucherRequestDTO() {
    }

    public BenefitVoucherRequestDTO(
        Integer employeeId,
        Double paidValue,
        LocalDate releaseDate,
        String description,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible
    ) {
        this.employeeId = employeeId;
        this.paidValue = paidValue;
        this.releaseDate = releaseDate;
        this.description = description;
        this.benefitType = benefitType;
        this.paymentStatus = paymentStatus;
        this.payrollDeductible = payrollDeductible;
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

    @Override
    public String toString() {
        return "BenefitVoucherRequestDTO{" +
            ", employeeId=" + employeeId +
            ", paidValue=" + paidValue +
            ", releaseDate=" + releaseDate +
            ", description='" + description + '\'' +
            ", benefitType=" + benefitType +
            ", paymentStatus=" + paymentStatus +
            ", payrollDeductible=" + payrollDeductible +
            '}';
    }
}