package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@Table(name = "installment")
public class Installment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_installment")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_event_id", referencedColumnName = "id_financial_event", nullable = false)
    private FinancialEvent financialEvent;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "installment_value", nullable = false)
    private Double installmentValue;

    @Column(name = "paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean paid;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Installment() {
    }

    public Installment(FinancialEvent financialEvent, Integer number, Double installmentValue, Boolean paid, EntityMetadata entityMetadata) {
        this.financialEvent = financialEvent;
        this.number = number;
        this.installmentValue = installmentValue;
        this.paid = paid;
        this.entityMetadata = entityMetadata;
    }

    public Installment(Integer id, FinancialEvent financialEvent, Integer number, Double installmentValue, Boolean paid, EntityMetadata entityMetadata) {
        this.id = id;
        this.financialEvent = financialEvent;
        this.number = number;
        this.installmentValue = installmentValue;
        this.paid = paid;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FinancialEvent getFinancialEvent() {
        return financialEvent;
    }

    public void setFinancialEvent(FinancialEvent financialEvent) {
        this.financialEvent = financialEvent;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(Double installmentValue) {
        this.installmentValue = installmentValue;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}