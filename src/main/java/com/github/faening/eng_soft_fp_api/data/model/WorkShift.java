package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "work_shift")
public record WorkShift(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_shift")
    Integer id,

    @Column(name = "description", length = 80, nullable = false)
    String description,

    @Column(name = "start_of_workday", nullable = false)
    @Temporal(TemporalType.TIME)
    Date startOfWorkday,

    @Column(name = "start_of_break", nullable = false)
    @Temporal(TemporalType.TIME)
    Date startOfBreak,

    @Column(name = "end_of_break", nullable = false)
    @Temporal(TemporalType.TIME)
    Date endOfBreak,

    @Column(name = "end_of_workday", nullable = false)
    @Temporal(TemporalType.TIME)
    Date endOfWorkday,

    @Column(name = "night_shift_allowance")
    Boolean nightShiftAllowance,

    @Column(name = "active", nullable = false)
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }