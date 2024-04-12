package com.github.faening.eng_soft_fp_api.domain.model.job;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ExperienceLevel;

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
    private Integer unhealthiness;
    private Integer departmentId;
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
        Integer unhealthiness,
        Integer departmentId,
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
        this.departmentId = departmentId;
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

    public Integer getUnhealthiness() {
        return unhealthiness;
    }

    public void setUnhealthiness(Integer unhealthiness) {
        this.unhealthiness = unhealthiness;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    @Override
    public String toString() {
        return "JobResponseDTO{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", experienceLevel=" + experienceLevel +
            ", baseSalary=" + baseSalary +
            ", dangerousness=" + dangerousness +
            ", unhealthiness=" + unhealthiness +
            ", departmentId=" + departmentId +
            ", enabled=" + enabled +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}