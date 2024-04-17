package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateTimeServiceAllowance implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final static Integer RUBRIC_CODE = 1206;

    @Autowired
    public CalculateTimeServiceAllowance(
        RubricService rubricService,
        TaxOrValueService taxOrValueService
    ) {
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
    }

    /**
     * Nota: O adicional de tempo de serviço é calculado para funcionários que possuem o atributo `timeServiceAllowance` como true ou que
     * possuem mais de 10 anos de admissão.
     */
    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .filter(employee -> employee.getTimeServiceAllowance() || LocalDate.now().getYear() - employee.getAdmissionDate().getYear() >= 10)
            .map(employee -> {
                RubricResponseDTO rubric = getRubricByCode();
                TaxOrValueResponseDTO taxOrValue = getTaxOrValueByType();
                BigDecimal calculatedValue = calculateTimeServiceAllowance(parameters);

                return new PayrollItemRequestDTO(
                    rubric,
                    taxOrValue,
                    employee.getSalary(),
                    calculatedValue,
                    taxOrValue.getTaxPercentage()
                );
            })
            .orElse(null);
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
     * Este método recupera o primeiro imposto ou valor pelo seu tipo.
     *
     * @return Um objeto TaxOrValueResponseDTO que representa o imposto ou valor recuperado. Retorna null se o imposto ou valor não for encontrado.
     */
    protected TaxOrValueResponseDTO getTaxOrValueByType() {
        return taxOrValueService.getByType(TaxOrValueType.TIME_SERVICE_ALLOWANCE).get(0);
    }

    /**
     * Este método calcula o adicional de tempo de serviço.
     *
     * @param parameters Os parâmetros de cálculo.
     * @return O valor calculado.
     */
    private BigDecimal calculateTimeServiceAllowance(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(employee -> {
                BigDecimal TimeServiceAllowancePercent = getTaxOrValueByType().getTaxPercentage().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                return employee.getSalary().multiply(TimeServiceAllowancePercent).setScale(2, RoundingMode.HALF_UP);
            })
            .orElse(BigDecimal.ZERO);
    }
}