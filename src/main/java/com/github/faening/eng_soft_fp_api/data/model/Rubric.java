package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.RubricType;
import jakarta.persistence.*;

import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@Table(name = "rubric")
public class Rubric implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubric")
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private Integer code;

    @Column(name = "name", length = 180, nullable = false)
    private String name;

    @Column(name = "kind", length = 180, nullable = false)
    private String kind;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RubricType type;

    @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enabled;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Rubric() {
    }

    public Rubric(
        Integer code,
        String name,
        String kind,
        String description,
        RubricType type,
        Boolean enabled,
        EntityMetadata entityMetadata
    ) {
        this.code = code;
        this.name = name;
        this.kind = kind;
        this.description = description;
        this.type = type;
        this.enabled = enabled;
        this.entityMetadata = entityMetadata;
    }

    public Rubric(
        Integer id,
        Integer code,
        String name,
        String kind,
        String description,
        RubricType type,
        Boolean enabled,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.kind = kind;
        this.description = description;
        this.type = type;
        this.enabled = enabled;
        this.entityMetadata = entityMetadata;
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

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}