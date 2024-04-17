package com.github.faening.eng_soft_fp_api.domain.calculation.generics;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.enumeration.HoursWorkedType;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public abstract class WorkedHoursCalculation {
    private final WorkShiftService workShiftService;
    private final HoursWorkedSheetService hoursWorkedSheetService;
    private final WorkingHoursConfig workingHoursConfig;

    @Autowired
    public WorkedHoursCalculation(
        WorkShiftService workShiftService,
        HoursWorkedSheetService hoursWorkedSheetService,
        WorkingHoursConfig workingHoursConfig
    ) {
        this.workShiftService = workShiftService;
        this.hoursWorkedSheetService = hoursWorkedSheetService;
        this.workingHoursConfig = workingHoursConfig;

    }

    /**
     * Este método calcula o total de dias úteis em um mês.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O total de dias úteis no mês.
     */
    protected Integer getWorkingDaysInMonth(CalculationParameters parameters) {
        return DateUtils.getWorkingDaysInMonth(parameters.getYear(), parameters.getMonth().getValue());
    }

    /**
     * Este método calcula o total de horas de trabalho em um mês.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O total de horas de trabalho no mês. Retorna null se o turno de trabalho do funcionário não for encontrado.
     */
    protected Integer getWorkingHoursInMonth(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(param -> {
                WorkShiftResponseDTO workShift = workShiftService.getById(param.getEmployee().getWorkShiftId());
                return Optional.ofNullable(workShift)
                    .map(shift -> {
                        int workingDaysInMonth = DateUtils.getWorkingDaysInMonth(param.getYear(), param.getMonth().getValue());
                        int reducedHours = workingHoursConfig.getReducedHours();
                        int fullTimeHours = workingHoursConfig.getFullTimeHours();

                        // Se o funcionário estiver em um turno reduzido, multiplica os dias úteis pelas horas reduzidas.
                        // Caso contrário, multiplica os dias úteis pelas horas de tempo integral.
                        return shift.getReducedShift() ? reducedHours * workingDaysInMonth : fullTimeHours * workingDaysInMonth;
                    })
                    .orElse(null);
            })
            .orElse(null);
    }

    /**
     * Este método recupera a folha de horas trabalhadas de um funcionário para um mês específico.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return Uma lista de objetos HoursWorkedSheetResponseDTO que representam as horas trabalhadas pelo funcionário no mês especificado.
     */
    protected List<HoursWorkedSheetResponseDTO> getHoursWorkedSheet(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(param -> {
                LocalDate[] startAndEndDate = DateUtils.getFirstAndLastDayOfMonth(param.getYear(), param.getMonth().getValue());
                LocalDate startDate = startAndEndDate[0];
                LocalDate endDate = startAndEndDate[1];
                return hoursWorkedSheetService.getWorkedHoursByEmployeeIdAndDateRange(param.getEmployee().getId(), startDate, endDate);
            })
            .orElse(null);
    }

    /**
     * Este método calcula o total de horas trabalhadas de um funcionário em um mês, em minutos
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @param hoursWorkedType O tipo de horas trabalhadas a serem calculadas. Pode ser REGULAR, NEGATIVE, OVERTIME50, OVERTIME100 ou TIME_BANK.
     * @return O total de horas trabalhadas no mês. Retorna 0 se a folha de horas trabalhadas não for encontrada.
     */
    @SuppressWarnings("SameParameterValue")
    protected BigDecimal calculateTotalHoursInMin(CalculationParameters parameters, HoursWorkedType hoursWorkedType) {
        List<HoursWorkedSheetResponseDTO> hoursWorkedSheet = getHoursWorkedSheet(parameters);
        return Optional.ofNullable(hoursWorkedSheet)
            .map(list -> list.stream()
                .mapToDouble(hours -> {
                    return switch (hoursWorkedType) {
                        case REGULAR -> DateUtils.toMinutes(hours.getRegularHours());
                        case NEGATIVE -> DateUtils.toMinutes(hours.getNegativeHours());
                        case OVERTIME50 -> DateUtils.toMinutes(hours.getOvertime50());
                        case OVERTIME100 -> DateUtils.toMinutes(hours.getOvertime100());
                        case TIME_BANK -> DateUtils.toMinutes(hours.getTimeBank());
                        default -> 0;
                    };
                })
                .sum())
            .map(BigDecimal::valueOf) // Convertendo o resultado para BigDecimal
            .map(bigDecimal -> bigDecimal.setScale(2, RoundingMode.HALF_UP)) // Ajustando para duas casas decimais
            .orElse(BigDecimal.ZERO);
    }
}