package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "hours_worked_sheet")
public class HoursWorkedSheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hours_worked_sheet")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "regular_hours")
    private LocalTime regularHours;

    @Column(name = "negative_hours")
    private LocalTime negativeHours;

    @Column(name = "overtime50")
    private LocalTime overtime50;

    @Column(name = "overtime100")
    private LocalTime overtime100;

    @Column(name = "time_bank")
    private LocalTime timeBank;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public HoursWorkedSheet() {
    }

    public HoursWorkedSheet(
        Integer id,
        Employee employee,
        LocalDate date,
        LocalTime regularHours,
        LocalTime negativeHours,
        LocalTime overtime50,
        LocalTime overtime100,
        LocalTime timeBank,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.regularHours = regularHours;
        this.negativeHours = negativeHours;
        this.overtime50 = overtime50;
        this.overtime100 = overtime100;
        this.timeBank = timeBank;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(LocalTime regularHours) {
        this.regularHours = regularHours;
    }

    public LocalTime getNegativeHours() {
        return negativeHours;
    }

    public void setNegativeHours(LocalTime negativeHours) {
        this.negativeHours = negativeHours;
    }

    public LocalTime getOvertime50() {
        return overtime50;
    }

    public void setOvertime50(LocalTime overtime50) {
        this.overtime50 = overtime50;
    }

    public LocalTime getOvertime100() {
        return overtime100;
    }

    public void setOvertime100(LocalTime overtime100) {
        this.overtime100 = overtime100;
    }

    public LocalTime getTimeBank() {
        return timeBank;
    }

    public void setTimeBank(LocalTime timeBank) {
        this.timeBank = timeBank;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}