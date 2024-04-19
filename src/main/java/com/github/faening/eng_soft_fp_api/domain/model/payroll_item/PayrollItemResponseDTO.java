package com.github.faening.eng_soft_fp_api.domain.model.payroll_item;

import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class PayrollItemResponseDTO implements Serializable {
    private Integer id;
    private Integer payrollId;
    private RubricResponseDTO rubric;
    private TaxOrValueResponseDTO taxOrValue;
    private BigDecimal baseValue;
    private BigDecimal calculatedValue;
    private BigDecimal reference;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PayrollItemResponseDTO() {
    }

    public PayrollItemResponseDTO(
        Integer id,
        Integer payrollId,
        RubricResponseDTO rubric,
        TaxOrValueResponseDTO taxOrValue,
        BigDecimal baseValue,
        BigDecimal calculatedValue,
        BigDecimal reference,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.payrollId = payrollId;
        this.rubric = rubric;
        this.taxOrValue = taxOrValue;
        this.baseValue = baseValue;
        this.calculatedValue = calculatedValue;
        this.reference = reference;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Integer payrollId) {
        this.payrollId = payrollId;
    }

    public RubricResponseDTO getRubric() {
        return rubric;
    }

    public void setRubric(RubricResponseDTO rubric) {
        this.rubric = rubric;
    }

    public TaxOrValueResponseDTO getTaxOrValue() {
        return taxOrValue;
    }

    public void setTaxOrValue(TaxOrValueResponseDTO taxOrValue) {
        this.taxOrValue = taxOrValue;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }

    public BigDecimal getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(BigDecimal calculatedValue) {
        this.calculatedValue = calculatedValue;
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

    public BigDecimal getReference() {
        return reference;
    }

    public void setReference(BigDecimal reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "PayrollItemResponseDTO{" +
            "id=" + id +
            ", payrollId=" + payrollId +
            ", rubric=" + rubric +
            ", taxOrValue=" + taxOrValue +
            ", baseValue=" + baseValue +
            ", calculatedValue=" + calculatedValue +
            ", reference=" + reference +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}