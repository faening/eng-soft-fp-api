package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employee_dependent")
public record EmployeeDependent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_dependent")
    Integer id,

    @Embedded
    Person person,

    @Column(name = "special_needs", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean specialNeeds,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
