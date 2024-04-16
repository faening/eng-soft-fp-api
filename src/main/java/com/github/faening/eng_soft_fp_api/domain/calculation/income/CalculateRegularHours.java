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
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateRegularHours extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final static Integer RUBRIC_CODE = 1000;

    @Autowired
    public CalculateRegularHours(
        RubricService rubricService,
        HoursWorkedSheetService hoursWorkedSheetService,
        WorkShiftService workShiftService,
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
            Integer employeeTotalHours = getEmployeeTotalHours(hoursWorkedSheet);
            BigDecimal employeeCalculatedSalary = calculateEmployeeSalary(parameters.getEmployee().getSalary(), workingHoursInMonth, employeeTotalHours);

            return new PayrollItemRequestDTO(
                rubric,
                null,
                parameters.getEmployee().getSalary(),
                employeeCalculatedSalary,
                BigDecimal.valueOf(employeeTotalHours)
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
     * Este método calcula o total de horas regulares trabalhadas por um funcionário, convertendo o tempo para horas.
     *
     * @param hoursWorkedSheet Uma lista de objetos HoursWorkedSheetResponseDTO que representam as horas trabalhadas pelo funcionário.
     * @return O total de horas regulares trabalhadas pelo funcionário.
     */
    protected Integer getEmployeeTotalHours(List<HoursWorkedSheetResponseDTO> hoursWorkedSheet) {
        int totalMinutes = hoursWorkedSheet
            .stream()
            .mapToInt(hours -> DateUtils.toMinutes(hours.getRegularHours()))
            .sum();
        return totalMinutes / 60;
    }

    /**
     * Este método calcula o salário de um funcionário com base nas horas trabalhadas.
     *
     * @param salary Salário base do funcionário.
     * @param workingHoursInMonth Total de horas de trabalho em um mês.
     * @param employeeTotalHours Total de horas trabalhadas pelo funcionário.
     * @return O salário calculado do funcionário. Se as horas trabalhadas pelo funcionário forem iguais às horas de trabalho em um mês, retorna o salário base. Caso contrário, calcula o valor da hora e multiplica pelas horas trabalhadas.
     */
    protected BigDecimal calculateEmployeeSalary(BigDecimal salary, int workingHoursInMonth, int employeeTotalHours) {
        // Se as horas trabalhadas pelo funcionário forem iguais às horas de trabalho em um mês, retorna o salário base
        if (Objects.equals(workingHoursInMonth, employeeTotalHours)) {
            return salary;
        } else {
            // Calcula o valor da hora com base no salário e nas horas de trabalho em um mês
            BigDecimal hourValue = EmployeeUtils.calculateHourlyRate(salary, workingHoursInMonth);
            // Multiplica o valor da hora pelas horas trabalhadas pelo funcionário para obter o salário calculado
            return hourValue.multiply(BigDecimal.valueOf(employeeTotalHours));
        }
    }
}