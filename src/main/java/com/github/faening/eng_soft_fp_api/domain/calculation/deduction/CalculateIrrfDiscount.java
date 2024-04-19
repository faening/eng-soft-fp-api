package com.github.faening.eng_soft_fp_api.domain.calculation.deduction;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.income.CalculateOvertime100Percent;
import com.github.faening.eng_soft_fp_api.domain.calculation.income.CalculateOvertime50Percent;
import com.github.faening.eng_soft_fp_api.domain.calculation.income.CalculateSalesAllowance;
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
public class CalculateIrrfDiscount implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final CalculateOvertime50Percent calculateOvertime50Percent;
    private final CalculateOvertime100Percent calculateOvertime100Percent;
    private final CalculateSalesAllowance calculateSalesAllowance;
    private final CalculateInssDiscount calculateInssDiscount;
    private final static Integer RUBRIC_CODE = 9203;

    @Autowired
    public CalculateIrrfDiscount(
        RubricService rubricService,
        TaxOrValueService taxOrValueService,
        CalculateOvertime50Percent calculateOvertime50Percent,
        CalculateOvertime100Percent calculateOvertime100Percent,
        CalculateSalesAllowance calculateSalesAllowance,
        CalculateInssDiscount calculateInssDiscount
    ) {
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
        this.calculateOvertime50Percent = calculateOvertime50Percent;
        this.calculateOvertime100Percent = calculateOvertime100Percent;
        this.calculateSalesAllowance = calculateSalesAllowance;
        this.calculateInssDiscount = calculateInssDiscount;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(this::calculateIrrfDiscount).filter(irrfDiscount -> irrfDiscount.compareTo(BigDecimal.ZERO) > 0).map(irrfDiscount -> new PayrollItemRequestDTO(
                getRubricByCode(),
                taxOrValueService.getIrrfPercentageByRange(parameters.getEmployee().getSalary()),
                calculateIrrfIncome(parameters),
                irrfDiscount,
                taxOrValueService.getIrrfPercentageByRange(parameters.getEmployee().getSalary()).getTaxPercentage()
            )).orElse(null);
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
    * Este método calcula o rendimento tributável para o cálculo do IRRF. O rendimento tributável é a soma do salário bruto do colaborador
    * com as horas extras, comissões e outros benefícios, subtraindo o desconto do INSS.
    *
    * @param parameters Parâmetros de cálculo.
    * @return O rendimento tributável.
    * */
    protected BigDecimal calculateIrrfIncome(CalculationParameters parameters) {
        BigDecimal totalIncome = parameters.getEmployee().getSalary();

        PayrollItemRequestDTO overtime50Percent = calculateOvertime50Percent.calculate(parameters);
        if (overtime50Percent != null && overtime50Percent.getCalculatedValue() != null) {
            totalIncome = totalIncome.add(overtime50Percent.getCalculatedValue());
        }

        PayrollItemRequestDTO overtime100Percent = calculateOvertime100Percent.calculate(parameters);
        if (overtime100Percent != null && overtime100Percent.getCalculatedValue() != null) {
            totalIncome = totalIncome.add(overtime100Percent.getCalculatedValue());
        }

        PayrollItemRequestDTO salesAllowance = calculateSalesAllowance.calculate(parameters);
        if (salesAllowance != null && salesAllowance.getCalculatedValue() != null) {
            totalIncome = totalIncome.add(salesAllowance.getCalculatedValue());
        }

        PayrollItemRequestDTO inssDiscount = calculateInssDiscount.calculate(parameters);
        if (inssDiscount != null && inssDiscount.getCalculatedValue() != null) {
            totalIncome = totalIncome.subtract(inssDiscount.getCalculatedValue());
        }

        return totalIncome;
    }

    /**
     * Este método calcula o desconto de IRRF.
     *
     * @param parameters Parâmetros de cálculo.
     * @return O valor do desconto de IRRF.
     */
    protected BigDecimal calculateIrrfDiscount(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getSalary)
            .map(salary -> {
                BigDecimal totalIncome = calculateIrrfIncome(parameters);
                TaxOrValueResponseDTO irrfPercentage = taxOrValueService.getIrrfPercentageByRange(salary);
                return totalIncome
                    .multiply(irrfPercentage.getTaxPercentage())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            })
            .orElse(BigDecimal.ZERO);
    }
}