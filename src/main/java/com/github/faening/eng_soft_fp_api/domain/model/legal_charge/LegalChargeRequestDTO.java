package com.github.faening.eng_soft_fp_api.domain.model.legal_charge;

import com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment.LegalChargeInstallmentRequestDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
public class LegalChargeRequestDTO implements Serializable {
    private Integer employeeId;
    private BigDecimal legalChargeAmount;
    private BigDecimal percentage;
    private LocalDate releaseDate;
    private Boolean isRecurring;
    private List<LegalChargeInstallmentRequestDTO> installments;

    public LegalChargeRequestDTO() {
    }

    public LegalChargeRequestDTO(
        Integer employeeId,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring,
        List<LegalChargeInstallmentRequestDTO> installments
    ) {
        this.employeeId = employeeId;
        this.legalChargeAmount = legalChargeAmount;
        this.percentage = percentage;
        this.releaseDate = releaseDate;
        this.isRecurring = isRecurring;
        this.installments = installments;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getLegalChargeAmount() {
        return legalChargeAmount;
    }

    public void setLegalChargeAmount(BigDecimal legalChargeAmount) {
        this.legalChargeAmount = legalChargeAmount;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean recurring) {
        isRecurring = recurring;
    }

    public List<LegalChargeInstallmentRequestDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LegalChargeInstallmentRequestDTO> installments) {
        this.installments = installments;
    }

    @Override
    public String toString() {
        return "LegalChargeRequestDTO{" +
            "employeeId=" + employeeId +
            ", legalChargeAmount=" + legalChargeAmount +
            ", percentage=" + percentage +
            ", releaseDate=" + releaseDate +
            ", isRecurring=" + isRecurring +
            ", installments=" + installments +
            '}';
    }
}