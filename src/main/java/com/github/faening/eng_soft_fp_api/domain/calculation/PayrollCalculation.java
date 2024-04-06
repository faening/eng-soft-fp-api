package com.github.faening.eng_soft_fp_api.domain.calculation;

import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;

@SuppressWarnings("unused")
public interface PayrollCalculation {
    PayrollItemRequestDTO calculate(CalculationParameters parameters);
}