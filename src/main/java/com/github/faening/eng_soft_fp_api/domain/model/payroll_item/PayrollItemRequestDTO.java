package com.github.faening.eng_soft_fp_api.domain.model.payroll_item;

import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
public class PayrollItemRequestDTO implements Serializable {
    private RubricResponseDTO rubric;
    private TaxOrValueResponseDTO taxOrValue;
    private BigDecimal baseValue;
    private BigDecimal calculatedValue;
    private BigDecimal reference;

    public PayrollItemRequestDTO() {
    }

    public PayrollItemRequestDTO(
        RubricResponseDTO rubric,
        TaxOrValueResponseDTO taxOrValue,
        BigDecimal baseValue,
        BigDecimal calculatedValue,
        BigDecimal reference
    ) {
        this.rubric = rubric;
        this.taxOrValue = taxOrValue;
        this.baseValue = baseValue;
        this.calculatedValue = calculatedValue;
        this.reference = reference;
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

    public BigDecimal getReference() {
        return reference;
    }

    public void setReference(BigDecimal reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "PayrollItemRequestDTO{" +
            "rubric=" + rubric +
            ", taxOrValue=" + taxOrValue +
            ", baseValue=" + baseValue +
            ", calculatedValue=" + calculatedValue +
            ", reference=" + reference +
            '}';
    }
}