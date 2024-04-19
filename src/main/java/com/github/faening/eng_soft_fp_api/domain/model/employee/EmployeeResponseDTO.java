package com.github.faening.eng_soft_fp_api.domain.model.employee;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class EmployeeResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private String rg;
    private String cpf;
    private LocalDate birthDate;
    private Gender gender;
    private String addressStreet;
    private String addressNumber;
    private String addressNeighborhood;
    private String addressComplement;
    private String addressCity;
    private BrazilianState addressUF;
    private String addressZipCode;
    private String phone;
    private String email;
    private LocalDate admissionDate;
    private Boolean timeServiceAllowance;
    private DepartmentResponseDTO department;
    private JobResponseDTO job;
    private WorkShiftResponseDTO workShift;
    private BigDecimal salary;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(
        Integer id,
        String name,
        String rg,
        String cpf,
        LocalDate birthDate,
        Gender gender,
        String addressStreet,
        String addressNumber,
        String addressNeighborhood,
        String addressComplement,
        String addressCity,
        BrazilianState addressUF,
        String addressZipCode,
        String phone,
        String email,
        LocalDate admissionDate,
        Boolean timeServiceAllowance,
        DepartmentResponseDTO department,
        JobResponseDTO job,
        WorkShiftResponseDTO workShift,
        BigDecimal salary,
        Boolean enabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.rg = rg;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.addressComplement = addressComplement;
        this.addressCity = addressCity;
        this.addressUF = addressUF;
        this.addressZipCode = addressZipCode;
        this.phone = phone;
        this.email = email;
        this.admissionDate = admissionDate;
        this.timeServiceAllowance = timeServiceAllowance;
        this.department = department;
        this.job = job;
        this.workShift = workShift;
        this.salary = salary;
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

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressNeighborhood() {
        return addressNeighborhood;
    }

    public void setAddressNeighborhood(String addressNeighborhood) {
        this.addressNeighborhood = addressNeighborhood;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public BrazilianState getAddressUF() {
        return addressUF;
    }

    public void setAddressUF(BrazilianState addressUF) {
        this.addressUF = addressUF;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Boolean getTimeServiceAllowance() {
        return timeServiceAllowance;
    }

    public void setTimeServiceAllowance(Boolean timeServiceAllowance) {
        this.timeServiceAllowance = timeServiceAllowance;
    }

    public DepartmentResponseDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentResponseDTO department) {
        this.department = department;
    }

    public JobResponseDTO getJob() {
        return job;
    }

    public void setJob(JobResponseDTO job) {
        this.job = job;
    }

    public WorkShiftResponseDTO getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShiftResponseDTO workShift) {
        this.workShift = workShift;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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
        return "EmployeeResponseDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", rg='" + rg + '\'' +
            ", cpf='" + cpf + '\'' +
            ", birthDate=" + birthDate +
            ", gender=" + gender +
            ", addressStreet='" + addressStreet + '\'' +
            ", addressNumber='" + addressNumber + '\'' +
            ", addressNeighborhood='" + addressNeighborhood + '\'' +
            ", addressComplement='" + addressComplement + '\'' +
            ", addressCity='" + addressCity + '\'' +
            ", addressUF=" + addressUF +
            ", addressZipCode='" + addressZipCode + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", admissionDate=" + admissionDate +
            ", timeServiceAllowance=" + timeServiceAllowance +
            ", department=" + department +
            ", job=" + job +
            ", workShift=" + workShift +
            ", salary=" + salary +
            ", enabled=" + enabled +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}