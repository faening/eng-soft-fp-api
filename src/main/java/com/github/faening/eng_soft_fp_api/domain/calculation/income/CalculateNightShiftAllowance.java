package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateNightShiftAllowance extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final WorkShiftService workShiftService;

    private final static Integer RUBRIC_CODE = 1205;
    private static final LocalTime NIGHT_START = LocalTime.of(22, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(5, 0);

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
            .map(workShift -> new PayrollItemRequestDTO(
                getRubricByCode(),
                taxOrValueService.getNightShiftAllowance(),
                parameters.getEmployee().getSalary(),
                calculateNightShiftAllowance(parameters, workShift),
                BigDecimal.valueOf((long) getNumberOfHoursWorkedAtNight(workShift) * getWorkingDaysInMonth(parameters))
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
     * Este método recupera um turno de trabalho pelo seu ID.
     *
     * @param workShiftId O ID do turno do funcionário.
     * @return Um objeto WorkShiftResponseDTO que representa o turno de trabalho recuperado. Retorna null se o trabalho não for encontrado.
     */
    protected WorkShiftResponseDTO getWorkShiftByEmployeeWorkShiftId(Integer workShiftId) {
        return workShiftService.getById(workShiftId);
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
                int nightHoursFirstPeriod = calculateNightHours(
                    shift.getStartOfWorkday(), shift.getStartOfBreak()
                );
                // Calcula as horas noturnas para o segundo período do turno
                int nightHoursSecondPeriod = calculateNightHours(
                    shift.getEndOfBreak(), shift.getEndOfWorkday()
                );
                return nightHoursFirstPeriod + nightHoursSecondPeriod;
            })
            .orElse(0);
    }

    /**
     * Este método calcula o número de horas noturnas trabalhadas em um turno.
     * As horas noturnas são definidas como o período entre 22:00 e 05:00.
     *
     * @param shiftStart A hora de início do turno.
     * @param shiftEnd A hora de término do turno.
     * @return O número de horas noturnas trabalhadas.
     */
    public static int calculateNightHours(LocalTime shiftStart, LocalTime shiftEnd) {
        // Se o turno começa e termina fora do período noturno, retorna 0
        if (shiftStart.isBefore(NIGHT_START) && shiftStart.isAfter(NIGHT_END) ||
            shiftEnd.isBefore(NIGHT_START) && shiftEnd.isAfter(NIGHT_END) && shiftStart.isAfter(NIGHT_START)) {
            return 0;
        }
        // Se o turno começa e termina no mesmo dia, calcula as horas noturnas para o mesmo dia
        if (shiftStart.isBefore(shiftEnd)) {
            return calculateNightHoursSameDay(shiftStart, shiftEnd);
            // Se o turno começa em um dia e termina no próximo, calcula as horas noturnas para o turno que se estende pela meia-noite
        } else {
            return calculateNightHoursOvernight(shiftStart, shiftEnd);
        }
    }

    /**
     * Este método calcula o número de horas noturnas trabalhadas em um turno que começa e termina no mesmo dia.
     * As horas noturnas são definidas como o período entre 22:00 e 05:00.
     *
     * @param shiftStart A hora de início do turno.
     * @param shiftEnd A hora de término do turno.
     * @return O número de horas noturnas trabalhadas.
     */
    private static int calculateNightHoursSameDay(LocalTime shiftStart, LocalTime shiftEnd) {
        // Define o início do período noturno. Se o turno começa antes do início do período noturno, o início do período noturno é considerado.
        // Se o turno começa depois do fim do período noturno, o início do turno é considerado.
        LocalTime nightStart = (shiftStart.isBefore(NIGHT_START) && shiftStart.isAfter(NIGHT_END)) ? NIGHT_START : shiftStart;
        // Define o fim do período noturno. Se o turno termina depois do fim do período noturno, o fim do período noturno é considerado.
        // Se o turno termina antes do início do período noturno, o fim do turno é considerado.
        LocalTime nightEnd = (shiftEnd.isAfter(NIGHT_END) && shiftEnd.isBefore(NIGHT_START)) ? NIGHT_END : shiftEnd;

        // Se o turno começa depois do fim do período noturno e termina antes do início do período noturno, o início e o fim do turno são considerados.
        if (shiftStart.isAfter(NIGHT_END) && shiftEnd.isBefore(NIGHT_START)) {
            nightStart = shiftStart;
            nightEnd = shiftEnd;
        }

        long minutes = ChronoUnit.MINUTES.between(nightStart, nightEnd);
        return (int) Math.ceil(minutes / 60.0);
    }

    /**
     * Este método calcula o número de horas noturnas trabalhadas em um turno que começa em um dia e termina no próximo.
     * As horas noturnas são definidas como o período entre 22:00 e 05:00.
     *
     * @param shiftStart A hora de início do turno.
     * @param shiftEnd A hora de término do turno.
     * @return O número de horas noturnas trabalhadas.
     */
    private static int calculateNightHoursOvernight(LocalTime shiftStart, LocalTime shiftEnd) {
        // Define o início do período noturno antes da meia-noite. Se o turno começa antes do início do período noturno, o início do período noturno é considerado.
        LocalTime nightStartBeforeMidnight = shiftStart.isBefore(NIGHT_START) ? NIGHT_START : shiftStart;
        long minutesBeforeMidnight = ChronoUnit.MINUTES.between(nightStartBeforeMidnight, LocalTime.MAX);
        int nightHoursBeforeMidnight = (int) Math.ceil(minutesBeforeMidnight / 60.0);

        // Define o fim do período noturno depois da meia-noite. Se o turno termina depois do fim do período noturno, o fim do período noturno é considerado.
        LocalTime nightEndAfterMidnight = shiftEnd.isAfter(NIGHT_END) ? NIGHT_END : shiftEnd;
        long minutesAfterMidnight = ChronoUnit.MINUTES.between(LocalTime.MIN, nightEndAfterMidnight);
        int nightHoursAfterMidnight = (int) Math.ceil(minutesAfterMidnight / 60.0);

        return nightHoursBeforeMidnight + nightHoursAfterMidnight;
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
                BigDecimal NightShiftAllowancePercentage = taxOrValueService
                    .getNightShiftAllowance().getTaxPercentage()
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                // Calcula o adicional noturno por hora de trabalho (valor da hora * percentual do adicional noturno)
                return employeeHourlyRate.multiply(NightShiftAllowancePercentage);
            })
            .map(value -> value.setScale(2, RoundingMode.HALF_UP))
            .orElse(BigDecimal.ZERO);
    }
}