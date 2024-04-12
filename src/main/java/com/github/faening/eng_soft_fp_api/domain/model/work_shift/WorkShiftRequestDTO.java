package com.github.faening.eng_soft_fp_api.domain.model.work_shift;

import java.io.Serializable;
import java.time.LocalTime;

@SuppressWarnings("unused")
public class WorkShiftRequestDTO implements Serializable {
    private String description;
    private LocalTime startOfWorkday;
    private LocalTime startOfBreak;
    private LocalTime endOfBreak;
    private LocalTime endOfWorkday;
    private Boolean nightShiftAllowance;
    private Boolean enabled;

    public WorkShiftRequestDTO() {
    }

    public WorkShiftRequestDTO(
        String description,
        LocalTime startOfWorkday,
        LocalTime startOfBreak,
        LocalTime endOfBreak,
        LocalTime endOfWorkday,
        Boolean nightShiftAllowance,
        Boolean enabled
    ) {
        this.description = description;
        this.startOfWorkday = startOfWorkday;
        this.startOfBreak = startOfBreak;
        this.endOfBreak = endOfBreak;
        this.endOfWorkday = endOfWorkday;
        this.nightShiftAllowance = nightShiftAllowance;
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "WorkShiftResponseDTO{" +
            ", description='" + description + '\'' +
            ", startOfWorkday=" + startOfWorkday +
            ", startOfBreak=" + startOfBreak +
            ", endOfBreak=" + endOfBreak +
            ", endOfWorkday=" + endOfWorkday +
            ", nightShiftAllowance=" + nightShiftAllowance +
            ", enabled=" + enabled +
            '}';
    }
}