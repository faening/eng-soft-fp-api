package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.DateUtils;
import com.github.faening.eng_soft_fp_api.util.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateRegularHours implements PayrollCalculation {
    private final RubricService rubricService;
    private final HoursWorkedSheetService hoursWorkedSheetService;
    private final WorkShiftService workShiftService;
    private final static Integer RUBRIC_CODE = 1000;

    @Autowired
    public CalculateRegularHours(
        RubricService rubricService,
        HoursWorkedSheetService hoursWorkedSheetService,
        WorkShiftService workShiftService
    ) {
        this.rubricService = rubricService;
        this.hoursWorkedSheetService = hoursWorkedSheetService;
        this.workShiftService = workShiftService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        if (parameters != null) {
            RubricResponseDTO rubric = getRubricByCode();
            Integer workingHoursInMonth = getWorkingHoursInMonth(parameters);
            List<HoursWorkedSheetResponseDTO> hoursWorkedSheet = getHoursWorkedSheetByEmployeeIdAndDate(parameters);
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

    protected RubricResponseDTO getRubricByCode() {
        return rubricService.getByCode(RUBRIC_CODE);
    }

    protected Integer getWorkingHoursInMonth(CalculationParameters parameters) {
        WorkShiftResponseDTO workShift = getWorkShiftByEmployeeId(parameters);
        // Se a propriedade `reducedShift` for verdadeira, a carga horária é de 6 horas diárias
        if (workShift != null) {
            return workShift.getReducedShift()
                ? 6 * DateUtils.getWorkingDaysInMonth(parameters.getYear(), parameters.getMonth().getValue())
                : 8 * DateUtils.getWorkingDaysInMonth(parameters.getYear(), parameters.getMonth().getValue());
        }
        return null;
    }

    protected WorkShiftResponseDTO getWorkShiftByEmployeeId(CalculationParameters parameters) {
        return workShiftService.getById(parameters.getEmployee().getWorkShiftId());
    }

    protected List<HoursWorkedSheetResponseDTO> getHoursWorkedSheetByEmployeeIdAndDate(CalculationParameters parameters) {
        LocalDate[] startAndEndDate = DateUtils.getFirstAndLastDayOfMonth(parameters.getYear(), parameters.getMonth().getValue());
        LocalDate startDate = startAndEndDate[0];
        LocalDate endDate = startAndEndDate[1];
        return hoursWorkedSheetService.getWorkedHoursByEmployeeIdAndDateRange(parameters.getEmployee().getId(), startDate, endDate);
    }

    protected Integer getEmployeeTotalHours(List<HoursWorkedSheetResponseDTO> hoursWorkedSheet) {
        int totalMinutes = hoursWorkedSheet
            .stream()
            .mapToInt(hours -> DateUtils.toMinutes(hours.getRegularHours()))
            .sum();
        return totalMinutes / 60;
    }

    protected BigDecimal calculateEmployeeSalary(BigDecimal salary, int workingHoursInMonth, int employeeTotalHours) {
        // Se o total de horas trabalhadas for igual ao total de horas do mês, o salário é retornado
        if (Objects.equals(workingHoursInMonth, employeeTotalHours)) {
            return salary;
        // Caso contrário, o valor da hora do funcionário é multiplicado pela quantidade de horas trabalhadas no mês
        } else {
            BigDecimal hourValue = EmployeeUtils.calculateHourlyRate(salary, workingHoursInMonth);
            return hourValue.multiply(BigDecimal.valueOf(employeeTotalHours));
        }
    }
}