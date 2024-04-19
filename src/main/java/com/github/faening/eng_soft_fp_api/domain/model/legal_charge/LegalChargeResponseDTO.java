package com.github.faening.eng_soft_fp_api.domain.model.legal_charge;

import com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment.LegalChargeInstallmentResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class LegalChargeResponseDTO implements Serializable {
    private Integer id;
    private Integer employeeId;
    private BigDecimal legalChargeAmount;
    private BigDecimal percentage;
    private LocalDate releaseDate;
    private Boolean isRecurring;
    private List<LegalChargeInstallmentResponseDTO> installments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LegalChargeResponseDTO() {
    }

    public LegalChargeResponseDTO(
        Integer id,
        Integer employeeId,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring,
        List<LegalChargeInstallmentResponseDTO> installments,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.legalChargeAmount = legalChargeAmount;
        this.percentage = percentage;
        this.releaseDate = releaseDate;
        this.isRecurring = isRecurring;
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

    public List<LegalChargeInstallmentResponseDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LegalChargeInstallmentResponseDTO> installments) {
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
        return "LegalChargeResponseDTO{" +
            "id=" + id +
            ", employeeId=" + employeeId +
            ", legalChargeAmount=" + legalChargeAmount +
            ", percentage=" + percentage +
            ", releaseDate=" + releaseDate +
            ", isRecurring=" + isRecurring +
            ", installments=" + installments +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}