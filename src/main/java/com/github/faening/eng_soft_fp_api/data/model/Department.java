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

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean active,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id_employee")
    Employee manager,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
