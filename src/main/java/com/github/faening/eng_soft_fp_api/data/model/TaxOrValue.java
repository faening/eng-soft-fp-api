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

    @Column(name = "name", length = 40, nullable = false)
    String name,

    @Column(name = "tax_percentage", nullable = false, precision = 5, scale = 2)
    BigDecimal taxPercentage,

    @Column(name = "minimum_wage", nullable = false, precision = 10, scale = 2)
    BigDecimal minimumWage,

    @Column(name = "maximum_wage", nullable = false, precision = 10, scale = 2)
    BigDecimal maximumWage,

    @Column(name = "start_date")
    Date startDate,

    @Column(name = "end_date")
    Date endDate,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
