package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.model.ExperienceLevel;
import com.github.faening.eng_soft_fp_api.domain.model.Status;
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
    @Enumerated(EnumType.ORDINAL)
    Status dangerousness,

    @Column(name = "unhealthiness", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status unhealthiness,

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    Department department,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    Status status,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
