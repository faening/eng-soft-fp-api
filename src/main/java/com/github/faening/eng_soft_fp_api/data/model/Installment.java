package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "installment")
public record Installment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_installment")
    Integer id,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financial_event_id", referencedColumnName = "id_financial_event", nullable = false)
    FinancialEvent financialEvent,

    @Column(name = "number", nullable = false)
    Integer number,

    @Column(name = "installment_value", nullable = false)
    Double installmentValue,

    @Column(name = "paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean paid,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
