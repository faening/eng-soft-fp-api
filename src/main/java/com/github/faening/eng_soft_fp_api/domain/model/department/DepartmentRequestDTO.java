package com.github.faening.eng_soft_fp_api.domain.model.department;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DepartmentRequestDTO implements Serializable {
    private String description;
    private Boolean enabled;
    private Integer managerId;

    public DepartmentRequestDTO() {
    }

    public DepartmentRequestDTO(String description, Boolean enabled, Integer managerId) {
        this.description = description;
        this.enabled = enabled;
        this.managerId = managerId;
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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}