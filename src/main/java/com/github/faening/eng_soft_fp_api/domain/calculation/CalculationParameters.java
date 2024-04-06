package com.github.faening.eng_soft_fp_api.domain.calculation;

import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.financial_event.FinancialEventResponseDTO;

@SuppressWarnings("unused")
public class CalculationParameters {
    private EmployeeSummaryDTO employee;
    private FinancialEventResponseDTO financialEvent;

    public CalculationParameters(EmployeeSummaryDTO employee, FinancialEventResponseDTO financialEvent) {
        this.employee = employee;
        this.financialEvent = financialEvent;
    }

    public EmployeeSummaryDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeSummaryDTO employee) {
        this.employee = employee;
    }

    public FinancialEventResponseDTO getFinancialEvent() {
        return financialEvent;
    }

    public void setFinancialEvent(FinancialEventResponseDTO financialEvent) {
        this.financialEvent = financialEvent;
    }
}