package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "department")
public record Department(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    Integer id,

    @Column(name = "description", length = 80, nullable = false)
    String description,

    @Column(name = "active", nullable = false)
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
