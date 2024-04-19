package com.github.faening.eng_soft_fp_api.domain.model.employee_dependent;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class EmployeeDependentResponseDTO implements Serializable {
    private Integer id;
    private Integer employeeId;
    private String name;
    private String rg;
    private String cpf;
    private LocalDate birthDate;
    private Gender gender;
    private Boolean specialNeeds;
    private Boolean familyAllowance;
    private Boolean daycareAllowance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployeeDependentResponseDTO() {
    }

    public EmployeeDependentResponseDTO(
        Integer id,
        Integer employeeId,
        String name,
        String rg,
        String cpf,
        LocalDate birthDate,
        Gender gender,
        Boolean specialNeeds,
        Boolean familyAllowance,
        Boolean daycareAllowance,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.rg = rg;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.specialNeeds = specialNeeds;
        this.familyAllowance = familyAllowance;
        this.daycareAllowance = daycareAllowance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
        return "EmployeeDependentResponseDTO{" +
            "id=" + id +
            ", employeeId=" + employeeId +
            ", name='" + name + '\'' +
            ", rg='" + rg + '\'' +
            ", cpf='" + cpf + '\'' +
            ", birthDate=" + birthDate +
            ", gender=" + gender +
            ", specialNeeds=" + specialNeeds +
            ", familyAllowance=" + familyAllowance +
            ", daycareAllowance=" + daycareAllowance +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}