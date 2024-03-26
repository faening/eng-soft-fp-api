package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ExperienceLevel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "job")
public record Job(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    Integer id,

    @Column(name = "description", length = 100, nullable = false)
    String description,

    @Column(name = "experience_level", nullable = false, columnDefinition = "DEFAULT 'ENTRY_LEVEL'")
    @Enumerated(EnumType.STRING)
    ExperienceLevel experienceLevel,

    @Column(name = "base_salary", nullable = false, precision = 10, scale = 2)
    BigDecimal baseSalary,

    @Column(name = "dangerousness", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean dangerousness,

    @Column(name = "unhealthiness", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean unhealthiness,

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    Department department,

    @Column(name = "active", nullable = false)
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
