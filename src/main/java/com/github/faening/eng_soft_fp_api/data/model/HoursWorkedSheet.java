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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    Date date,

    @Column(name = "regular_hours")
    @Temporal(TemporalType.TIME)
    Date regularHours,

    @Column(name = "negative_hours")
    @Temporal(TemporalType.TIME)
    Date negativeHours,

    @Column(name = "overtime50")
    @Temporal(TemporalType.TIME)
    Date overtime50,

    @Column(name = "overtime100")
    @Temporal(TemporalType.TIME)
    Date overtime100,

    @Column(name = "time_bank")
    @Temporal(TemporalType.TIME)
    Date timeBank,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }