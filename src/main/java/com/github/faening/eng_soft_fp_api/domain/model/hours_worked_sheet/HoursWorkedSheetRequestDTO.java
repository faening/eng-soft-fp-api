package com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@SuppressWarnings("unused")
public class HoursWorkedSheetRequestDTO implements Serializable {
    private Integer employeeId;
    private LocalDate date;
    private LocalTime regularHours;
    private LocalTime negativeHours;
    private LocalTime overtime50;
    private LocalTime overtime100;
    private LocalTime timeBank;

    public HoursWorkedSheetRequestDTO() {
    }

    public HoursWorkedSheetRequestDTO(
        Integer employeeId,
        LocalDate date,
        LocalTime regularHours,
        LocalTime negativeHours,
        LocalTime overtime50,
        LocalTime overtime100,
        LocalTime timeBank
    ) {
        this.employeeId = employeeId;
        this.date = date;
        this.regularHours = regularHours;
        this.negativeHours = negativeHours;
        this.overtime50 = overtime50;
        this.overtime100 = overtime100;
        this.timeBank = timeBank;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoursWorkedSheetRequestDTO that = (HoursWorkedSheetRequestDTO) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(date, that.date) && Objects.equals(regularHours, that.regularHours) && Objects.equals(negativeHours, that.negativeHours) && Objects.equals(overtime50, that.overtime50) && Objects.equals(overtime100, that.overtime100) && Objects.equals(timeBank, that.timeBank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, date, regularHours, negativeHours, overtime50, overtime100, timeBank);
    }

    @Override
    public String toString() {
        return "HoursWorkedSheetRequestDTO{" +
            "employeeId=" + employeeId +
            ", date=" + date +
            ", regularHours=" + regularHours +
            ", negativeHours=" + negativeHours +
            ", overtime50=" + overtime50 +
            ", overtime100=" + overtime100 +
            ", timeBank=" + timeBank +
            '}';
    }
}