package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Entity
@Table(name = "payroll_item")
public class PayrollItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payroll_item")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payroll_id", nullable = false, referencedColumnName = "id_payroll")
    private Payroll payroll;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rubric_id",  nullable = false, referencedColumnName = "id_rubric")
    private Rubric rubric;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tax_or_value_id", referencedColumnName = "id_tax_or_value")
    private TaxOrValue taxOrValue;

    @Column(name = "base_value", precision = 10, scale = 2)
    private BigDecimal baseValue;

    @Column(name = "calculated_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal calculatedValue;

    @Column(name = "reference", nullable = false, precision = 10, scale = 2)
    private BigDecimal reference;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public PayrollItem() {
    }

    public PayrollItem(
        Integer id,
        Payroll payroll,
        Rubric rubric,
        TaxOrValue taxOrValue,
        BigDecimal baseValue,
        BigDecimal calculatedValue,
        BigDecimal reference,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.payroll = payroll;
        this.rubric = rubric;
        this.taxOrValue = taxOrValue;
        this.baseValue = baseValue;
        this.calculatedValue = calculatedValue;
        this.reference = reference;
        this.entityMetadata = entityMetadata;
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

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}