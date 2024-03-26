package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PayrollStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "payroll")
public record Payroll(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payroll")
    Integer id,

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id_company", nullable = false)
    Company company,

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Column(name = "month", nullable = false)
    @Enumerated(EnumType.STRING)
    Month month,

    @Column(name = "year", nullable = false)
    Integer year,

    @Column(name = "gross_salary", nullable = false, precision = 10, scale = 2)
    BigDecimal grossSalary,

    @Column(name = "inss_base_amount", nullable = false, precision = 10, scale = 2)
    BigDecimal inssBaseAmount,

    @Column(name = "inss_tax_rate", nullable = false, precision = 5, scale = 2)
    BigDecimal inssTaxRate,

    @Column(name = "irrf_base_amout", nullable = false, precision = 10, scale = 2)
    BigDecimal irrfBaseAmount,

    @Column(name = "irrf_tax_rate", nullable = false, precision = 5, scale = 2)
    BigDecimal irrTaxRate,

    @Column(name = "fgts_base_amout", nullable = false, precision = 10, scale = 2)
    BigDecimal fgtsBaseAmount,

    @Column(name = "fgts_payed", nullable = false, precision = 10, scale = 2)
    BigDecimal fgtsPayed,

    @Column(name = "total_additions", nullable = false, precision = 10, scale = 2)
    BigDecimal totalAdditions,

    @Column(name = "total_discounts", nullable = false, precision = 10, scale = 2)
    BigDecimal totalDiscounts,

    @Column(name = "total_liquid", nullable = false, precision = 10, scale = 2)
    BigDecimal totalLiquid,

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PayrollItem> items,

    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    PayrollStatus status,

    @Column(name = "notes")
    String notes,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }