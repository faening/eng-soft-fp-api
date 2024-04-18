package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.enumeration.HoursWorkedType;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateDaycareAllowance extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final EmployeeService employeeService;
    private final EmployeeDependentService employeeDependentService;
    private final static Integer RUBRIC_CODE = 1406;

    public CalculateDaycareAllowance(
        RubricService rubricService,
        TaxOrValueService taxOrValueService,
        EmployeeService employeeService,
        EmployeeDependentService employeeDependentService,
        WorkShiftService workShiftService,
        HoursWorkedSheetService hoursWorkedSheetService,
        WorkingHoursConfig workingHoursConfig
    ) {
        super(workShiftService, hoursWorkedSheetService, workingHoursConfig);
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
        this.employeeService = employeeService;
        this.employeeDependentService = employeeDependentService;
    }

    /**
     * Este método calcula o auxílio creche de um funcionário com base no valor fixo do auxílio creche e no total de horas trabalhadas.
     * Este calculo é feito somente se o número de funcionárias mulheres for maior que 30 e se o funcionário tiver dependentes com direito ao auxílio creche.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O valor calculado do auxílio creche do funcionário.
     */
    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .filter(empService -> employeeService.getEmployeeCountByGender(Gender.FEMALE) >= 30)
            .filter(param -> getDependentsByEmployeeWithDaycareAllowance(param.getEmployee()) > 0)
            .map(param -> new PayrollItemRequestDTO(
                getRubricByCode(),
                taxOrValueService.getDaycareAllowance(),
                calculateTotalHoursInMin(parameters, HoursWorkedType.REGULAR)
                    .setScale(0, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP),
                calculateDaycareAllowance(parameters),
                taxOrValueService.getDaycareAllowance().getFixedValue()
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
     * Este método recupera a quantidade de dependentes de um funcionário que possuem direito ao auxílio créche.
     *
     * @param employee O funcionário que terá a quantidade de dependentes com direito ao auxílio família recuperada.
     * @return A quantidade de dependentes do funcionário que possuem direito ao auxílio família.
     */
    protected Integer getDependentsByEmployeeWithDaycareAllowance(EmployeeSummaryDTO employee) {
        long count = employeeDependentService.getByEmployeeId(employee.getId())
            .stream()
            .filter(EmployeeDependentResponseDTO::getDaycareAllowance)
            .count();
        return Math.toIntExact(count);
    }

    /**
    * Este método calcula o auxílio creche de um funcionário com base no valor fixo do auxílio creche e no total de horas trabalhadas.
     *
    * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
    * @return O valor calculado do auxílio creche do funcionário.
    */
    protected BigDecimal calculateDaycareAllowance(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(params -> {
                // Calcula o valor do auxílio creche por hora
                BigDecimal allowanceValuePerDay = taxOrValueService
                    .getDaycareAllowance().getFixedValue()
                    .divide(BigDecimal.valueOf(8), 3, RoundingMode.HALF_UP);
                // Calcula o total de horas trabalhadas pelo funcionário
                BigDecimal employeeTotalHours = calculateTotalHoursInMin(params, HoursWorkedType.REGULAR).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
                return employeeTotalHours.multiply(allowanceValuePerDay);
            })
            .map(value -> value.setScale(2, RoundingMode.HALF_UP))
            .orElse(BigDecimal.ZERO);
    }
}