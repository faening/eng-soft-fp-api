package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tax_or_value")
public record TaxOrValue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tax_or_value")
    Integer id,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    TaxOrValueType type,

    @Column(name = "range_id")
    Integer range,

    @Column(name = "range_minimum_wage", precision = 10, scale = 2)
    BigDecimal rangeMinimumWage,

    @Column(name = "range_maximum_wage", precision = 10, scale = 2)
    BigDecimal rangeMaximumWage,

    @Column(name = "fixed_value", precision = 10, scale = 2)
    BigDecimal fixedValue,

    @Column(name = "tax_percentage", precision = 5, scale = 2)
    BigDecimal taxPercentage,

    @Column(name = "description", length = 160, nullable = false)
    String description,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
