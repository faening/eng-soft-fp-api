package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.HoursWorkedType;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateOvertime100Percent extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final static Integer RUBRIC_CODE = 1003;
    private final static BigDecimal OVERTIME_100_PERCENT_FACTOR = BigDecimal.valueOf(2);

    @Autowired
    public CalculateOvertime100Percent(
        RubricService rubricService,
        WorkShiftService workShiftService,
        HoursWorkedSheetService hoursWorkedSheetService,
        WorkingHoursConfig workingHoursConfig
    ) {
        super(workShiftService, hoursWorkedSheetService, workingHoursConfig);
        this.rubricService = rubricService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .filter(params -> calculateTotalHoursInMin(parameters, HoursWorkedType.OVERTIME100).compareTo(BigDecimal.ZERO) > 0)
            .map(param -> new PayrollItemRequestDTO(
                getRubricByCode(),
                null,
                param.getEmployee().getSalary(),
                calculateEmployeeOvertime100InMin(parameters),
                calculateTotalHoursInMin(parameters, HoursWorkedType.OVERTIME100)
            ))
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
     * Este método calcula o valor total das horas extras (com acréscimo de 100%) trabalhadas por um funcionário.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O valor total das horas extras (com acréscimo de 100%) trabalhadas pelo funcionário. Retorna zero se o funcionário não trabalhou horas extras.
     */
    protected BigDecimal calculateEmployeeOvertime100InMin(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getSalary)
            .map(salary -> {
                BigDecimal hourlyValueWorked = EmployeeUtils.calculateHourlyRate(salary, getWorkingHoursInMonth(parameters));
                BigDecimal employeeTotalOvertime100InMin = calculateTotalHoursInMin(parameters, HoursWorkedType.OVERTIME100);

                // Calcula o valor da hora extra (com acréscimo de 100%), convertendo o valor para minutos
                BigDecimal overtimeValueInMin = hourlyValueWorked
                    .multiply(OVERTIME_100_PERCENT_FACTOR)
                    .divide(BigDecimal.valueOf(60), 4, RoundingMode.HALF_UP);

                return overtimeValueInMin.multiply(employeeTotalOvertime100InMin).setScale(2, RoundingMode.HALF_UP);
            })
            .orElse(BigDecimal.ZERO);
    }
}