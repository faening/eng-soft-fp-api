package com.github.faening.eng_soft_fp_api.domain.model.rubric;

import com.github.faening.eng_soft_fp_api.domain.enumeration.RubricType;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class RubricResponseDTO implements Serializable {
    private Integer id;
    private Integer code;
    private String name;
    private String kind;
    private String description;
    private RubricType type;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RubricResponseDTO() {
    }

    public RubricResponseDTO(
        Integer id,
        Integer code,
        String name,
        String kind,
        String description,
        RubricType type,
        Boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.kind = kind;
        this.description = description;
        this.type = type;
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
        return "RubricResponseDTO{" +
            "id=" + id +
            ", code=" + code +
            ", name='" + name + '\'' +
            ", kind='" + kind + '\'' +
            ", description='" + description + '\'' +
            ", type=" + type +
            ", enabled=" + enabled +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}