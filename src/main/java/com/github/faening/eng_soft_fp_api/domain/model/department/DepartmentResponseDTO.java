package com.github.faening.eng_soft_fp_api.domain.model.department;

import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class DepartmentResponseDTO implements Serializable {
    private Integer id;
    private String description;
    private Boolean enabled;
    private EmployeeSummaryDTO manager;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DepartmentResponseDTO() {
    }

    public DepartmentResponseDTO(
        Integer id,
        String description,
        Boolean enabled,
        EmployeeSummaryDTO manager,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.description = description;
        this.enabled = enabled;
        this.manager = manager;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public EmployeeSummaryDTO getManager() {
        return manager;
    }

    public void setManager(EmployeeSummaryDTO manager) {
        this.manager = manager;
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