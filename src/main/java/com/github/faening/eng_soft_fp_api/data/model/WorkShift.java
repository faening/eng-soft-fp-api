package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;

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
    LocalTime startOfWorkday,

    @Column(name = "start_of_break", nullable = false)
    LocalTime startOfBreak,

    @Column(name = "end_of_break", nullable = false)
    LocalTime endOfBreak,

    @Column(name = "end_of_workday", nullable = false)
    LocalTime endOfWorkday,

    @Column(name = "night_shift_allowance", columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean nightShiftAllowance,

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }