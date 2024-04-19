package com.github.faening.eng_soft_fp_api.domain.calculation.deduction;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateInssDiscount implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final static Integer RUBRIC_CODE = 9201;

    @Autowired
    public CalculateInssDiscount(RubricService rubricService, TaxOrValueService taxOrValueService) {
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        BigDecimal inssDiscount = calculateInssDiscount(parameters);
        return inssDiscount.compareTo(BigDecimal.ZERO) > 0
            ? new PayrollItemRequestDTO(
            getRubricByCode(),
            taxOrValueService.getInssPercentageByRange(parameters.getEmployee().getSalary()),
            parameters.getEmployee().getSalary(),
            inssDiscount,
            taxOrValueService.getInssPercentageByRange(parameters.getEmployee().getSalary()).getTaxPercentage()
        ) : null;
    }

    /**
     * Este método recupera uma rubrica pelo seu código.
     *
     * @return Um objeto RubricResponseDTO que representa a rubrica recuperada.
     */
    public RubricResponseDTO getRubricByCode() {
        return rubricService.getByCode(RUBRIC_CODE);
    }

    /**
     * Este método calcula o desconto de INSS.
     *
     * @param parameters Parâmetros de cálculo.
     * @return O valor do desconto de INSS.
     */
    protected BigDecimal calculateInssDiscount(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getSalary)
            .map(salary -> {
                TaxOrValueResponseDTO inssPercentage = taxOrValueService.getInssPercentageByRange(salary);
                return salary.multiply(inssPercentage.getTaxPercentage().divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);
            })
            .orElse(BigDecimal.ZERO);
    }
}