package com.github.faening.eng_soft_fp_api.domain.model.job;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ExperienceLevel;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class JobResponseDTO implements Serializable {
    private Integer id;
    private String description;
    private ExperienceLevel experienceLevel;
    private BigDecimal baseSalary;
    private Boolean dangerousness;
    private Boolean unhealthiness;
    private DepartmentResponseDTO department;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JobResponseDTO() {
    }

    public JobResponseDTO(
        Integer id,
        String description,
        ExperienceLevel experienceLevel,
        BigDecimal baseSalary,
        Boolean dangerousness,
        Boolean unhealthiness,
        DepartmentResponseDTO department,
        Boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.description = description;
        this.experienceLevel = experienceLevel;
        this.baseSalary = baseSalary;
        this.dangerousness = dangerousness;
        this.unhealthiness = unhealthiness;
        this.department = department;
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

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Boolean getDangerousness() {
        return dangerousness;
    }

    public void setDangerousness(Boolean dangerousness) {
        this.dangerousness = dangerousness;
    }

    public Boolean getUnhealthiness() {
        return unhealthiness;
    }

    public void setUnhealthiness(Boolean unhealthiness) {
        this.unhealthiness = unhealthiness;
    }

    public DepartmentResponseDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponseDTO department) {
        this.department = department;
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