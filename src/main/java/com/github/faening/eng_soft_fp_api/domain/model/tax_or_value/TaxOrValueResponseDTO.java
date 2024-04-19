package com.github.faening.eng_soft_fp_api.domain.model.tax_or_value;

import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class TaxOrValueResponseDTO implements Serializable {
    private Integer id;
    private TaxOrValueType type;
    private Integer range;
    private BigDecimal rangeMinimumWage;
    private BigDecimal rangeMaximumWage;
    private BigDecimal fixedValue;
    private BigDecimal taxPercentage;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TaxOrValueResponseDTO() {
    }

    public TaxOrValueResponseDTO(
        Integer id,
        TaxOrValueType type,
        Integer range,
        BigDecimal rangeMinimumWage,
        BigDecimal rangeMaximumWage,
        BigDecimal fixedValue,
        BigDecimal taxPercentage,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.type = type;
        this.range = range;
        this.rangeMinimumWage = rangeMinimumWage;
        this.rangeMaximumWage = rangeMaximumWage;
        this.fixedValue = fixedValue;
        this.taxPercentage = taxPercentage;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaxOrValueType getType() {
        return type;
    }

    public void setType(TaxOrValueType type) {
        this.type = type;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public BigDecimal getRangeMinimumWage() {
        return rangeMinimumWage;
    }

    public void setRangeMinimumWage(BigDecimal rangeMinimumWage) {
        this.rangeMinimumWage = rangeMinimumWage;
    }

    public BigDecimal getRangeMaximumWage() {
        return rangeMaximumWage;
    }

    public void setRangeMaximumWage(BigDecimal rangeMaximumWage) {
        this.rangeMaximumWage = rangeMaximumWage;
    }

    public BigDecimal getFixedValue() {
        return fixedValue;
    }

    public void setFixedValue(BigDecimal fixedValue) {
        this.fixedValue = fixedValue;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "TaxOrValueResponseDTO{" +
            "id=" + id +
            ", type=" + type +
            ", range=" + range +
            ", rangeMinimumWage=" + rangeMinimumWage +
            ", rangeMaximumWage=" + rangeMaximumWage +
            ", fixedValue=" + fixedValue +
            ", taxPercentage=" + taxPercentage +
            ", description='" + description + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}