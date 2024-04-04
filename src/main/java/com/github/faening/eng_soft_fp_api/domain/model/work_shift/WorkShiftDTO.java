package com.github.faening.eng_soft_fp_api.domain.model.work_shift;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("unused")
public class WorkShiftDTO {
    private Integer id;
    private String description;
    private LocalTime startOfWorkday;
    private LocalTime startOfBreak;
    private LocalTime endOfBreak;
    private LocalTime endOfWorkday;
    private Boolean nightShiftAllowance;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public WorkShiftDTO() {
    }

    public WorkShiftDTO(
        Integer id,
        String description,
        LocalTime startOfWorkday,
        LocalTime startOfBreak,
        LocalTime endOfBreak,
        LocalTime endOfWorkday,
        Boolean nightShiftAllowance,
        Boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.description = description;
        this.startOfWorkday = startOfWorkday;
        this.startOfBreak = startOfBreak;
        this.endOfBreak = endOfBreak;
        this.endOfWorkday = endOfWorkday;
        this.nightShiftAllowance = nightShiftAllowance;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}