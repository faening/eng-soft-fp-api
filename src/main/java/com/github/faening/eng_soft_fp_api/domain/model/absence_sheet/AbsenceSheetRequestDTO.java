package com.github.faening.eng_soft_fp_api.domain.model.absence_sheet;

import com.github.faening.eng_soft_fp_api.domain.enumeration.AbsenceType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class AbsenceSheetRequestDTO implements Serializable {
    private Integer employeeId;
    private AbsenceType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String observation;
    private ApprovalStatus status;

    public AbsenceSheetRequestDTO() {
    }

    public AbsenceSheetRequestDTO(
        Integer employeeId,
        AbsenceType type,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String observation,
        ApprovalStatus status
    ) {
        this.employeeId = employeeId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.observation = observation;
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public AbsenceType getType() {
        return type;
    }

    public void setType(AbsenceType type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AbsenceSheetRequestDTO{" +
            "employeeId=" + employeeId +
            ", type=" + type +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", observation='" + observation + '\'' +
            ", status=" + status +
            '}';
    }
}