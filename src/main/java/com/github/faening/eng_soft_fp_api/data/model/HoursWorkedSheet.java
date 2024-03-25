package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hours_worked_sheet")
public record HoursWorkedSheet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hours_worked_sheet")
    Integer id,

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    Date releaseDate,

    @Column(name = "normal_hours")
    @Temporal(TemporalType.TIME)
    Date normalHours,

    @Column(name = "negative_hours")
    @Temporal(TemporalType.TIME)
    Date negativeHours,

    @Column(name = "extra_hours_50")
    @Temporal(TemporalType.TIME)
    Date extraHours50,

    @Column(name = "extra_hours_100")
    @Temporal(TemporalType.TIME)
    Date extraHours100,

    @Column(name = "hours_bank")
    @Temporal(TemporalType.TIME)
    Date hoursBank,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }