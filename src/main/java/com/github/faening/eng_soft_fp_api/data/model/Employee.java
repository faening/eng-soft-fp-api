package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Integer id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "name")),
        @AttributeOverride(name = "rg", column = @Column(name = "rg")),
        @AttributeOverride(name = "cpf", column = @Column(name = "cpf")),
        @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date")),
        @AttributeOverride(name = "gender", column = @Column(name = "gender")),
    })
    private Person person;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "addressStreet", column = @Column(name = "address_street")),
        @AttributeOverride(name = "addressNumber", column = @Column(name = "address_number")),
        @AttributeOverride(name = "addressNeighborhood", column = @Column(name = "address_neighborhood")),
        @AttributeOverride(name = "addressComplement", column = @Column(name = "address_complement")),
        @AttributeOverride(name = "addressCity", column = @Column(name = "address_city")),
        @AttributeOverride(name = "addressUF", column = @Column(name = "address_uf")),
        @AttributeOverride(name = "addressZipCode", column = @Column(name = "address_zip_code"))
    })
    private Address address;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id_job", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "work_shift_id", referencedColumnName = "id_work_shift", nullable = false)
    private WorkShift workShift;

    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EmployeeDependent> dependents;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AbsenceSheet> absenceSheets;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<HoursWorkedSheet> hoursWorkedSheets;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FinancialEvent> financialEvents;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BenefitVoucher> benefitVouchers;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Department> managedDepartments;

    @Column(name = "enabled", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enabled;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Employee() {
    }

    public Employee(Integer id, Person person, Address address, String phone, String email, LocalDate admissionDate, Department department, Job job, WorkShift workShift, BigDecimal salary, List<EmployeeDependent> dependents, List<AbsenceSheet> absenceSheets, List<HoursWorkedSheet> hoursWorkedSheets, List<FinancialEvent> financialEvents, List<BenefitVoucher> benefitVouchers, List<Department> managedDepartments, Boolean enabled, EntityMetadata entityMetadata) {
        this.id = id;
        this.person = person;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.admissionDate = admissionDate;
        this.department = department;
        this.job = job;
        this.workShift = workShift;
        this.salary = salary;
        this.dependents = dependents;
        this.absenceSheets = absenceSheets;
        this.hoursWorkedSheets = hoursWorkedSheets;
        this.financialEvents = financialEvents;
        this.benefitVouchers = benefitVouchers;
        this.managedDepartments = managedDepartments;
        this.enabled = enabled;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public List<EmployeeDependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<EmployeeDependent> dependents) {
        this.dependents = dependents;
    }

    public List<AbsenceSheet> getAbsenceSheets() {
        return absenceSheets;
    }

    public void setAbsenceSheets(List<AbsenceSheet> absenceSheets) {
        this.absenceSheets = absenceSheets;
    }

    public List<HoursWorkedSheet> getHoursWorkedSheets() {
        return hoursWorkedSheets;
    }

    public void setHoursWorkedSheets(List<HoursWorkedSheet> hoursWorkedSheets) {
        this.hoursWorkedSheets = hoursWorkedSheets;
    }

    public List<FinancialEvent> getFinancialEvents() {
        return financialEvents;
    }

    public void setFinancialEvents(List<FinancialEvent> financialEvents) {
        this.financialEvents = financialEvents;
    }

    public List<BenefitVoucher> getBenefitVouchers() {
        return benefitVouchers;
    }

    public void setBenefitVouchers(List<BenefitVoucher> benefitVouchers) {
        this.benefitVouchers = benefitVouchers;
    }

    public List<Department> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<Department> managedDepartments) {
        this.managedDepartments = managedDepartments;
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