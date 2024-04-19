package com.github.faening.eng_soft_fp_api.domain.calculation;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;

@SuppressWarnings("unused")
public class CalculationParameters {
    private EmployeeSummaryDTO employee;
    private Month month;
    private Integer year;

    public CalculationParameters() {
    }

    public CalculationParameters(EmployeeSummaryDTO employee, Month month, Integer year) {
        this.employee = employee;
        this.month = month;
        this.year = year;
    }

    public EmployeeSummaryDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeSummaryDTO employee) {
        this.employee = employee;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}