package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@Table(name = "employee_dependent")
public class EmployeeDependent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_dependent")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "name")),
        @AttributeOverride(name = "rg", column = @Column(name = "rg")),
        @AttributeOverride(name = "cpf", column = @Column(name = "cpf")),
        @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date")),
        @AttributeOverride(name = "gender", column = @Column(name = "gender")),
    })
    private Person person;

    @Column(name = "special_needs", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean specialNeeds;

    @Column(name = "family_allowance", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean familyAllowance;

    @Column(name = "daycare_allowance", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean daycareAllowance;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public EmployeeDependent() {
    }

    public EmployeeDependent(
        Integer id,
        Employee employee,
        Person person,
        Boolean specialNeeds,
        Boolean familyAllowance,
        Boolean daycareAllowance,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.person = person;
        this.specialNeeds = specialNeeds;
        this.familyAllowance = familyAllowance;
        this.daycareAllowance = daycareAllowance;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(Boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public Boolean getFamilyAllowance() {
        return familyAllowance;
    }

    public void setFamilyAllowance(Boolean familyAllowance) {
        this.familyAllowance = familyAllowance;
    }

    public Boolean getDaycareAllowance() {
        return daycareAllowance;
    }

    public void setDaycareAllowance(Boolean daycareAllowance) {
        this.daycareAllowance = daycareAllowance;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}