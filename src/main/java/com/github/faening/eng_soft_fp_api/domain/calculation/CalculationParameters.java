package com.github.faening.eng_soft_fp_api.domain.calculation;

import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;

@SuppressWarnings("unused")
public class CalculationParameters {
    private EmployeeSummaryDTO employee;

    public CalculationParameters(EmployeeSummaryDTO employee) {
        this.employee = employee;
    }

    public EmployeeSummaryDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeSummaryDTO employee) {
        this.employee = employee;
    }
}