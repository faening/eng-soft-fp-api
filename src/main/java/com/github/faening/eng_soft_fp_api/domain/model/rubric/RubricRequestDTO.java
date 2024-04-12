package com.github.faening.eng_soft_fp_api.domain.model.rubric;

import com.github.faening.eng_soft_fp_api.domain.enumeration.RubricType;

@SuppressWarnings("unused")
public class RubricRequestDTO {
    private Integer code;
    private String name;
    private String kind;
    private String description;
    private RubricType type;
    private Boolean enabled;

    public RubricRequestDTO() {
    }

    public RubricRequestDTO(Integer code, String name, String kind, String description, RubricType type, Boolean enabled) {
        this.code = code;
        this.name = name;
        this.kind = kind;
        this.description = description;
        this.type = type;
        this.enabled = enabled;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RubricType getType() {
        return type;
    }

    public void setType(RubricType type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "RubricRequestDTO{" +
            "code=" + code +
            ", name='" + name + '\'' +
            ", kind='" + kind + '\'' +
            ", description='" + description + '\'' +
            ", type=" + type +
            ", enabled=" + enabled +
            '}';
    }
}