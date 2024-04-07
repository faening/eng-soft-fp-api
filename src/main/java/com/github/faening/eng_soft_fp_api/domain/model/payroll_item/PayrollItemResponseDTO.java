package com.github.faening.eng_soft_fp_api.domain.model.payroll_item;

import com.github.faening.eng_soft_fp_api.data.model.Payroll;
import com.github.faening.eng_soft_fp_api.data.model.Rubric;
import com.github.faening.eng_soft_fp_api.data.model.TaxOrValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class PayrollItemResponseDTO implements Serializable {
    private Integer id;
    private Payroll payroll;
    private Rubric rubric;
    private TaxOrValue taxOrValue;
    private Integer financialEventId;
    private BigDecimal baseValue;
    private BigDecimal calculatedValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PayrollItemResponseDTO() {
    }

    public PayrollItemResponseDTO(
        Integer id,
        Payroll payroll,
        Rubric rubric,
        TaxOrValue taxOrValue,
        Integer financialEventId,
        BigDecimal baseValue,
        BigDecimal calculatedValue,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.payroll = payroll;
        this.rubric = rubric;
        this.taxOrValue = taxOrValue;
        this.financialEventId = financialEventId;
        this.baseValue = baseValue;
        this.calculatedValue = calculatedValue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public TaxOrValue getTaxOrValue() {
        return taxOrValue;
    }

    public void setTaxOrValue(TaxOrValue taxOrValue) {
        this.taxOrValue = taxOrValue;
    }

    public Integer getFinancialEventId() {
        return financialEventId;
    }

    public void setFinancialEventId(Integer financialEventId) {
        this.financialEventId = financialEventId;
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
}