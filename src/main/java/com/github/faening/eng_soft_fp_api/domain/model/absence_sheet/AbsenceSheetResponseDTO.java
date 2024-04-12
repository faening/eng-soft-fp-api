package com.github.faening.eng_soft_fp_api.domain.model.absence_sheet;

import com.github.faening.eng_soft_fp_api.domain.enumeration.AbsenceType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class AbsenceSheetResponseDTO implements Serializable {
    private Integer id;
    private Integer employeeId;
    private AbsenceType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String observation;
    private ApprovalStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AbsenceSheetResponseDTO() {
    }

    public AbsenceSheetResponseDTO(
        Integer id,
        Integer employeeId,
        AbsenceType type,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String observation,
        ApprovalStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.observation = observation;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AbsenceSheetResponseDTO{" +
            "id=" + id +
            ", employeeId=" + employeeId +
            ", type=" + type +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", observation='" + observation + '\'' +
            ", status=" + status +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}