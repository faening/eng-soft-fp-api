package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.RubricType;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "rubric")
public record Rubric(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubric")
    Integer id,

    @Column(name = "description", length = 120, nullable = false)
    String description,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    RubricType type,

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
