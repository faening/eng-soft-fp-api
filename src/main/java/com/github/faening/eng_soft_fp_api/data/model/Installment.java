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

        @Column(name = "number", nullable = false)
        Integer number,

        @Column(name = "value", nullable = false)
        Double value,

        @Column(name = "paid", nullable = false)
        Boolean paid,

        @Embedded
        EntityMetadata entityMetadata
) implements Serializable { }
