package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public record Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    Integer id,

    @Embedded
    Person person,

    @Embedded
    Address address,

    @Column(name = "phone", length = 11)
    String phone,

    @Column(name = "email", length = 80)
    String email,

    @Column(name = "admission_date", nullable = false)
    @Temporal(TemporalType.DATE)
    Date admissionDate,

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    Department department,

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id_job", nullable = false)
    Job job,

    @ManyToOne
    @JoinColumn(name = "work_shift_id", referencedColumnName = "id_work_shift", nullable = false)
    WorkShift workShift,

    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    BigDecimal salary,

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<EmployeeDependent> dependents,

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<AbsenceSheet> absenceSheets,

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<HoursWorkedSheet> hoursWorkedSheets,

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<FinancialEvent> financialEvents,

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<BenefitVoucher> benefitVouchers,

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Department managedDepartment,

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    Boolean active,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }