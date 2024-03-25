package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ExperienceLevel;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "job")
public record Job(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    Integer id,

    @Column(name = "description", length = 100, nullable = false)
    String description,

    @Column(name = "experience_level", nullable = false)
    @Enumerated(EnumType.STRING)
    ExperienceLevel experienceLevel,

    @Column(name = "base_salary", nullable = false)
    Double baseSalary,

    @Column(name = "dangerousness", nullable = false)
    Boolean dangerousness,

    @Column(name = "unhealthiness", nullable = false)
    Boolean unhealthiness,

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    Department department,

    @Column(name = "active", nullable = false)
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
