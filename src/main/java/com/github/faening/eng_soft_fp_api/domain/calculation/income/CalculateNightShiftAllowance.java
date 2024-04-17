package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.NightShiftCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateNightShiftAllowance extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final WorkShiftService workShiftService;
    private final static Integer RUBRIC_CODE = 1205;

    @Autowired
    public CalculateNightShiftAllowance(
        RubricService rubricService,
        TaxOrValueService taxOrValueService,
        WorkShiftService workShiftService,
        HoursWorkedSheetService hoursWorkedSheetService,
        WorkingHoursConfig workingHoursConfig
    ) {
        super(workShiftService, hoursWorkedSheetService, workingHoursConfig);
        this.workShiftService = workShiftService;
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getWorkShiftId)
            .map(this::getWorkShiftByEmployeeWorkShiftId)
            .filter(WorkShiftResponseDTO::getNightShiftAllowance)
            .map(workShift -> {
                RubricResponseDTO rubric = getRubricByCode();
                TaxOrValueResponseDTO taxOrValue = getTaxOrValueByType();
                BigDecimal calculatedValue = calculateNightShiftAllowance(parameters, workShift);

                return new PayrollItemRequestDTO(
                    rubric,
                    taxOrValue,
                    parameters.getEmployee().getSalary(),
                    calculatedValue,
                    BigDecimal.valueOf((long) getNumberOfHoursWorkedAtNight(workShift) * getWorkingDaysInMonth(parameters))
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
     * Este método recupera um turno de trabalho pelo seu ID.
     *
     * @param workShiftId O ID do turno do funcionário.
     * @return Um objeto WorkShiftResponseDTO que representa o turno de trabalho recuperado. Retorna null se o trabalho não for encontrado.
     */
    protected WorkShiftResponseDTO getWorkShiftByEmployeeWorkShiftId(Integer workShiftId) {
        return workShiftService.getById(workShiftId);
    }

    /**
     * Este método recupera o primeiro imposto ou valor pelo seu tipo.
     *
     * @return Um objeto TaxOrValueResponseDTO que representa o imposto ou valor recuperado. Retorna null se o imposto ou valor não for encontrado.
     */
    protected TaxOrValueResponseDTO getTaxOrValueByType() {
        return taxOrValueService.getByType(TaxOrValueType.NIGHT_SHIFT_ALLOWANCE).get(0);
    }

    /**
     * Este método calcula o adicional noturno total para um funcionário em um mês específico, com base no turno de trabalho do funcionário.
     * O calculo considera somente as horas trabalhadas à noite (22h às 5h).
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @param workShift O turno de trabalho do funcionário.
     * @return O adicional noturno total para o funcionário no mês especificado.
     */
    protected BigDecimal calculateNightShiftAllowance(CalculationParameters parameters, WorkShiftResponseDTO workShift) {
        return Optional.ofNullable(parameters)
            .map(params -> {
                // Calcula o número de horas trabalhadas à noite em um turno de trabalho
                int nightHoursPerDay = getNumberOfHoursWorkedAtNight(workShift);
                // Calcula o número de dias úteis em um mês
                int workingDaysInMonth = getWorkingDaysInMonth(params);
                // Calcula o adicional noturno por hora de trabalho
                BigDecimal nightShiftAllowancePerHour = calculateHourlyRate(params);
                // Calcula o adicional noturno total (adicional noturno por hora * horas noturnas por dia * dias úteis no mês)
                return nightShiftAllowancePerHour.multiply(BigDecimal.valueOf(nightHoursPerDay)).multiply(BigDecimal.valueOf(workingDaysInMonth));
            })
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Este método calcula o número de horas trabalhadas à noite em um turno de trabalho.
     *
     * @param workShift O turno de trabalho para o qual as horas noturnas devem ser calculadas.
     * @return O número de horas trabalhadas à noite no turno de trabalho.
     */
    protected Integer getNumberOfHoursWorkedAtNight(WorkShiftResponseDTO workShift) {
        return Optional.ofNullable(workShift)
            .map(shift -> {
                // Calcula as horas noturnas para o primeiro período do turno
                int nightHoursFirstPeriod = NightShiftCalculation.calculateNightHours(
                    shift.getStartOfWorkday(), shift.getStartOfBreak()
                );
                // Calcula as horas noturnas para o segundo período do turno
                int nightHoursSecondPeriod = NightShiftCalculation.calculateNightHours(
                    shift.getEndOfBreak(), shift.getEndOfWorkday()
                );
                return nightHoursFirstPeriod + nightHoursSecondPeriod;
            })
            .orElse(0);
    }

    /**
     * Este método calcula o adicional noturno por hora de trabalho com base no valor da hora do funcionário.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O adicional noturno por hora de trabalho.
     */
    protected BigDecimal calculateHourlyRate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getSalary)
            .map(salary -> {
                // Calcula o valor da hora do funcionário
                BigDecimal employeeHourlyRate = EmployeeUtils.calculateHourlyRate(salary, getWorkingHoursInMonth(parameters));
                // Calcula o percentual do adicional noturno
                BigDecimal NightShiftAllowancePercentage = getTaxOrValueByType().getTaxPercentage().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                // Calcula o adicional noturno por hora de trabalho (valor da hora * percentual do adicional noturno)
                return employeeHourlyRate.multiply(NightShiftAllowancePercentage);
            })
            .orElse(BigDecimal.ZERO);
    }
}