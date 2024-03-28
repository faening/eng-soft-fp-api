package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "hours_worked_sheet")
public record HoursWorkedSheet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hours_worked_sheet")
    Integer id,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Column(name = "date", nullable = false)
    LocalDate date,

    @Column(name = "regular_hours")
    LocalTime regularHours,

    @Column(name = "negative_hours")
    LocalTime negativeHours,

    @Column(name = "overtime50")
    LocalTime overtime50,

    @Column(name = "overtime100")
    LocalTime overtime100,

    @Column(name = "time_bank")
    LocalTime timeBank,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }