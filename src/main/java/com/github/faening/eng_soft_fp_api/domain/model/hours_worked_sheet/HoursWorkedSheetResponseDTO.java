package com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet;

import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@SuppressWarnings("unused")
public class HoursWorkedSheetResponseDTO implements Serializable {
    private Integer id;
    private EmployeeSummaryDTO employee;
    private LocalDate date;
    private LocalTime regularHours;
    private LocalTime negativeHours;
    private LocalTime overtime50;
    private LocalTime overtime100;
    private LocalTime timeBank;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public HoursWorkedSheetResponseDTO() {
    }

    public HoursWorkedSheetResponseDTO(
        Integer id,
        EmployeeSummaryDTO employee,
        LocalDate date,
        LocalTime regularHours,
        LocalTime negativeHours,
        LocalTime overtime50,
        LocalTime overtime100,
        LocalTime timeBank,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.regularHours = regularHours;
        this.negativeHours = negativeHours;
        this.overtime50 = overtime50;
        this.overtime100 = overtime100;
        this.timeBank = timeBank;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeSummaryDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeSummaryDTO employee) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoursWorkedSheetResponseDTO that = (HoursWorkedSheetResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) && Objects.equals(date, that.date) && Objects.equals(regularHours, that.regularHours) && Objects.equals(negativeHours, that.negativeHours) && Objects.equals(overtime50, that.overtime50) && Objects.equals(overtime100, that.overtime100) && Objects.equals(timeBank, that.timeBank) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, date, regularHours, negativeHours, overtime50, overtime100, timeBank, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "HoursWorkedSheetResponseDTO{" +
            "id=" + id +
            ", employee=" + employee +
            ", date=" + date +
            ", regularHours=" + regularHours +
            ", negativeHours=" + negativeHours +
            ", overtime50=" + overtime50 +
            ", overtime100=" + overtime100 +
            ", timeBank=" + timeBank +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}