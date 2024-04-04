package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "work_shift")
public class WorkShift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_shift")
    private Integer id;

    @Column(name = "description", length = 80, nullable = false)
    private String description;

    @Column(name = "start_of_workday", nullable = false)
    private LocalTime startOfWorkday;

    @Column(name = "start_of_break", nullable = false)
    private LocalTime startOfBreak;

    @Column(name = "end_of_break", nullable = false)
    private LocalTime endOfBreak;

    @Column(name = "end_of_workday", nullable = false)
    private LocalTime endOfWorkday;

    @Column(name = "night_shift_allowance", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean nightShiftAllowance;

    @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enabled;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public WorkShift() {
    }

    public WorkShift(
        Integer id,
        String description,
        LocalTime startOfWorkday,
        LocalTime startOfBreak,
        LocalTime endOfBreak,
        LocalTime endOfWorkday,
        Boolean nightShiftAllowance,
        Boolean enabled,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.description = description;
        this.startOfWorkday = startOfWorkday;
        this.startOfBreak = startOfBreak;
        this.endOfBreak = endOfBreak;
        this.endOfWorkday = endOfWorkday;
        this.nightShiftAllowance = nightShiftAllowance;
        this.enabled = enabled;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartOfWorkday() {
        return startOfWorkday;
    }

    public void setStartOfWorkday(LocalTime startOfWorkday) {
        this.startOfWorkday = startOfWorkday;
    }

    public LocalTime getStartOfBreak() {
        return startOfBreak;
    }

    public void setStartOfBreak(LocalTime startOfBreak) {
        this.startOfBreak = startOfBreak;
    }

    public LocalTime getEndOfBreak() {
        return endOfBreak;
    }

    public void setEndOfBreak(LocalTime endOfBreak) {
        this.endOfBreak = endOfBreak;
    }

    public LocalTime getEndOfWorkday() {
        return endOfWorkday;
    }

    public void setEndOfWorkday(LocalTime endOfWorkday) {
        this.endOfWorkday = endOfWorkday;
    }

    public Boolean getNightShiftAllowance() {
        return nightShiftAllowance;
    }

    public void setNightShiftAllowance(Boolean nightShiftAllowance) {
        this.nightShiftAllowance = nightShiftAllowance;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}