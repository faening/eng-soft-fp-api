package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.DateUtils;
import com.github.faening.eng_soft_fp_api.util.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateOvertime50Percent extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final static Integer RUBRIC_CODE = 1003;
    private final static BigDecimal OVERTIME_50_PERCENT_FACTOR = BigDecimal.valueOf(1.5);

    @Autowired
    public CalculateOvertime50Percent(
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
        if (parameters != null) {
            RubricResponseDTO rubric = getRubricByCode();
            Integer workingHoursInMonth = getWorkingHoursInMonth(parameters);
            List<HoursWorkedSheetResponseDTO> hoursWorkedSheet = getHoursWorkedSheet(parameters);
            Integer employeeTotalOvertime50 = getEmployeeTotalOvertime50InMin(hoursWorkedSheet);
            BigDecimal employeeCalculatedOvertime50 = calculateEmployeeOvertime50InMin(parameters.getEmployee().getSalary(), workingHoursInMonth, employeeTotalOvertime50);

            return new PayrollItemRequestDTO(
                rubric,
                null,
                parameters.getEmployee().getSalary(),
                employeeCalculatedOvertime50,
                BigDecimal.valueOf(employeeTotalOvertime50)
            );
        }
        return null;
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
     * Este método calcula o total de horas extras (com acréscimo de 50%) trabalhadas por um funcionário, convertendo o tempo para minutos.
     *
     * @param hoursWorkedSheet Uma lista de objetos HoursWorkedSheetResponseDTO que representam as horas trabalhadas pelo funcionário.
     * @return O total de horas extras (com acréscimo de 50%) trabalhadas pelo funcionário em minutos.
     */
    protected Integer getEmployeeTotalOvertime50InMin(List<HoursWorkedSheetResponseDTO> hoursWorkedSheet) {
        return hoursWorkedSheet
            .stream()
            .mapToInt(hours -> DateUtils.toMinutes(hours.getOvertime50()))
            .sum();
    }

    /**
     * Este método calcula o valor total das horas extras (com acréscimo de 50%) trabalhadas por um funcionário.
     *
     * @param employeeSalary Salário do funcionário.
     * @param hoursWorkedPerMonthInMin Total de horas trabalhadas pelo funcionário no mês, em minutos.
     * @param employeeTotalOvertime50InMin Total de horas extras (com acréscimo de 50%) trabalhadas pelo funcionário, em minutos.
     * @return O valor total das horas extras (com acréscimo de 50%) trabalhadas pelo funcionário. Retorna zero se o funcionário não trabalhou horas extras.
     */
    protected BigDecimal calculateEmployeeOvertime50InMin(BigDecimal employeeSalary, int hoursWorkedPerMonthInMin, int employeeTotalOvertime50InMin) {
        BigDecimal hourlyValueWorked = EmployeeUtils.calculateHourlyRate(employeeSalary, hoursWorkedPerMonthInMin);

        // Calcula o valor da hora extra (com acréscimo de 50%), convertendo o valor para minutos
        BigDecimal overtimeValueInMin = hourlyValueWorked
            .multiply(OVERTIME_50_PERCENT_FACTOR)
            .divide(BigDecimal.valueOf(60), 4, RoundingMode.HALF_UP);

        return (employeeTotalOvertime50InMin > 0
            ? overtimeValueInMin.multiply(BigDecimal.valueOf(employeeTotalOvertime50InMin))
            : BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }
}