package com.github.faening.eng_soft_fp_api.domain.model.job;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ExperienceLevel;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class JobRequestDTO implements Serializable {
    private String description;
    private ExperienceLevel experienceLevel;
    private BigDecimal baseSalary;
    private Boolean dangerousness;
    private Boolean unhealthiness;
    private Integer departmentId;
    private Boolean enabled;

    public JobRequestDTO() {
    }

    public JobRequestDTO(
        String description,
        ExperienceLevel experienceLevel,
        BigDecimal baseSalary,
        Boolean dangerousness,
        Boolean unhealthiness,
        Integer departmentId,
        Boolean enabled
    ) {
        this.description = description;
        this.experienceLevel = experienceLevel;
        this.baseSalary = baseSalary;
        this.dangerousness = dangerousness;
        this.unhealthiness = unhealthiness;
        this.departmentId = departmentId;
        this.enabled = enabled;
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

    @Override
    public String toString() {
        return "JobRequestDTO{" +
            "description='" + description + '\'' +
            ", experienceLevel=" + experienceLevel +
            ", baseSalary=" + baseSalary +
            ", dangerousness=" + dangerousness +
            ", unhealthiness=" + unhealthiness +
            ", departmentId=" + departmentId +
            ", enabled=" + enabled +
            '}';
    }
}