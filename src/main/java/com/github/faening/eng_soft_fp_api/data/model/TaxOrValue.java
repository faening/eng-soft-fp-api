package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Entity
@Table(name = "tax_or_value")
public class TaxOrValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tax_or_value")
    private Integer id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxOrValueType type;

    @Column(name = "range_id")
    private Integer range;

    @Column(name = "range_minimum_wage", precision = 10, scale = 2)
    private BigDecimal rangeMinimumWage;

    @Column(name = "range_maximum_wage", precision = 10, scale = 2)
    private BigDecimal rangeMaximumWage;

    @Column(name = "fixed_value", precision = 10, scale = 2)
    private BigDecimal fixedValue;

    @Column(name = "tax_percentage", precision = 5, scale = 2)
    private BigDecimal taxPercentage;

    @Column(name = "description", length = 160, nullable = false)
    private String description;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public TaxOrValue() {
    }

    public TaxOrValue(
        TaxOrValueType type,
        Integer range,
        BigDecimal rangeMinimumWage,
        BigDecimal rangeMaximumWage,
        BigDecimal fixedValue,
        BigDecimal taxPercentage,
        String description,
        EntityMetadata entityMetadata
    ) {
        this.type = type;
        this.range = range;
        this.rangeMinimumWage = rangeMinimumWage;
        this.rangeMaximumWage = rangeMaximumWage;
        this.fixedValue = fixedValue;
        this.taxPercentage = taxPercentage;
        this.description = description;
        this.entityMetadata = entityMetadata;
    }

    public TaxOrValue(
        Integer id,
        TaxOrValueType type,
        Integer range,
        BigDecimal rangeMinimumWage,
        BigDecimal rangeMaximumWage,
        BigDecimal fixedValue,
        BigDecimal taxPercentage,
        String description,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.type = type;
        this.range = range;
        this.rangeMinimumWage = rangeMinimumWage;
        this.rangeMaximumWage = rangeMaximumWage;
        this.fixedValue = fixedValue;
        this.taxPercentage = taxPercentage;
        this.description = description;
        this.entityMetadata = entityMetadata;
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

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}