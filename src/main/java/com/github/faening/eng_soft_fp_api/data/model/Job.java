package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ExperienceLevel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Entity
@Table(name = "job")
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job")
    private Integer id;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "experience_level", nullable = false, columnDefinition = "DEFAULT 'ENTRY_LEVEL'")
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Column(name = "base_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal baseSalary;

    @Column(name = "dangerousness", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean dangerousness;

    @Column(name = "unhealthiness", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean unhealthiness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    private Department department;

    @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enabled;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Job() {
    }

    public Job(
        String description,
        ExperienceLevel experienceLevel,
        BigDecimal baseSalary,
        Boolean dangerousness,
        Boolean unhealthiness,
        Department department,
        Boolean enabled,
        EntityMetadata entityMetadata
    ) {
        this.description = description;
        this.experienceLevel = experienceLevel;
        this.baseSalary = baseSalary;
        this.dangerousness = dangerousness;
        this.unhealthiness = unhealthiness;
        this.department = department;
        this.enabled = enabled;
        this.entityMetadata = entityMetadata;
    }

    public Job(
        Integer id,
        String description,
        ExperienceLevel experienceLevel,
        BigDecimal baseSalary,
        Boolean dangerousness,
        Boolean unhealthiness,
        Department department,
        Boolean enabled,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.description = description;
        this.experienceLevel = experienceLevel;
        this.baseSalary = baseSalary;
        this.dangerousness = dangerousness;
        this.unhealthiness = unhealthiness;
        this.department = department;
        this.enabled = enabled;
        this.entityMetadata = entityMetadata;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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