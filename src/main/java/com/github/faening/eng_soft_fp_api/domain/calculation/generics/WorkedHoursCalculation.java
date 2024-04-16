package com.github.faening.eng_soft_fp_api.domain.calculation.generics;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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
     * Este método calcula o total de horas de trabalho em um mês.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O total de horas de trabalho no mês. Retorna null se o turno de trabalho do funcionário não for encontrado.
     */
    protected Integer getWorkingHoursInMonth(CalculationParameters parameters) {
        // Recupera o turno de trabalho do funcionário. Com base no turno de trabalho, calcula o total de horas de trabalho no mês.
        WorkShiftResponseDTO workShift = workShiftService.getById(parameters.getEmployee().getWorkShiftId());
        if (workShift != null) {
            int workingDaysInMonth = DateUtils.getWorkingDaysInMonth(parameters.getYear(), parameters.getMonth().getValue());
            int reducedHours = workingHoursConfig.getReducedHours();
            int fullTimeHours = workingHoursConfig.getFullTimeHours();

            // Se o funcionário estiver em um turno reduzido, multiplica os dias úteis pelas horas reduzidas.
            // Caso contrário, multiplica os dias úteis pelas horas de tempo integral.
            return workShift.getReducedShift() ? reducedHours * workingDaysInMonth : fullTimeHours * workingDaysInMonth;
        }
        return null;
    }

    /**
     * Este método recupera a folha de horas trabalhadas de um funcionário para um mês específico.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return Uma lista de objetos HoursWorkedSheetResponseDTO que representam as horas trabalhadas pelo funcionário no mês especificado.
     */
    protected List<HoursWorkedSheetResponseDTO> getHoursWorkedSheet(CalculationParameters parameters) {
        LocalDate[] startAndEndDate = DateUtils.getFirstAndLastDayOfMonth(parameters.getYear(), parameters.getMonth().getValue());
        LocalDate startDate = startAndEndDate[0];
        LocalDate endDate = startAndEndDate[1];
        return hoursWorkedSheetService.getWorkedHoursByEmployeeIdAndDateRange(parameters.getEmployee().getId(), startDate, endDate);
    }
}