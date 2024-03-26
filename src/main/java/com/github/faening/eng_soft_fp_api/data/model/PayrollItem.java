package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll_item")
public record PayrollItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payroll_item")
    Integer id,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_id", referencedColumnName = "id_payroll", nullable = false)
    Payroll payroll,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rubric_id", referencedColumnName = "id_rubric", nullable = false)
    Rubric rubric,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_or_value_id", referencedColumnName = "id_tax_or_value")
    TaxOrValue taxOrValue,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_event_id", referencedColumnName = "id_financial_event")
    FinancialEvent  financialEvent,

    @Column(name = "base_value", nullable = false, precision = 10, scale = 2)
    BigDecimal baseValue,

    @Column(name = "calculated_value", nullable = false, precision = 10, scale = 2)
    BigDecimal calculatedValue,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
