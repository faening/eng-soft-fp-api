package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.HoursWorkedType;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
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
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateRegularHours extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final static Integer RUBRIC_CODE = 1000;

    @Autowired
    public CalculateRegularHours(
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
            .map(param -> {
                RubricResponseDTO rubric = getRubricByCode();
                Integer workingHoursInMonth = getWorkingHoursInMonth(param);
                List<HoursWorkedSheetResponseDTO> hoursWorkedSheet = getHoursWorkedSheet(param);
                BigDecimal employeeCalculatedSalary = calculateEmployeeSalary(parameters);

                return new PayrollItemRequestDTO(
                    rubric,
                    null,
                    param.getEmployee().getSalary(),
                    employeeCalculatedSalary,
                    getEmployeeTotalHours(parameters)
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
     * Este método recupera a lista de horas trabalhadas de um funcionário.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return A lista de horas trabalhadas de um funcionário.
     */
    protected BigDecimal getEmployeeTotalHours(CalculationParameters parameters) {
        return calculateTotalHoursInMin(parameters, HoursWorkedType.REGULAR).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
    }

    /**
     * Este método calcula o salário de um funcionário com base nas horas trabalhadas.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O salário calculado do funcionário. Se as horas trabalhadas pelo funcionário forem iguais às horas de trabalho em um mês, retorna o salário base.
     * Caso contrário, calcula o valor da hora e multiplica pelas horas trabalhadas.
     */
    protected BigDecimal calculateEmployeeSalary(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getSalary)
            .map(salary -> {
                int workingHoursInMonth = getWorkingHoursInMonth(parameters);
                BigDecimal employeeTotalHours = getEmployeeTotalHours(parameters);

                // TODO: Verficar se existem ausências cadastradas e se elas devem ser descontadas do salário.

                if (BigDecimal.valueOf(workingHoursInMonth).compareTo(employeeTotalHours) == 0) {
                    return salary;
                } else {
                    BigDecimal employeeHourlyRate = EmployeeUtils.calculateHourlyRate(salary, workingHoursInMonth);
                    return employeeHourlyRate.multiply(employeeTotalHours).setScale(2, RoundingMode.HALF_UP);
                }
            })
            .orElse(BigDecimal.ZERO);
    }
}