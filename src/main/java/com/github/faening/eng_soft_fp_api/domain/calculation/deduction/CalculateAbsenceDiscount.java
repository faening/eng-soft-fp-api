package com.github.faening.eng_soft_fp_api.domain.calculation.deduction;

import com.github.faening.eng_soft_fp_api.config.WorkingHoursConfig;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.calculation.generics.WorkedHoursCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.AbsenceSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import com.github.faening.eng_soft_fp_api.util.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateAbsenceDiscount extends WorkedHoursCalculation implements PayrollCalculation {
    private final RubricService rubricService;
    private final AbsenceSheetService absenceSheetService;
    private final static Integer RUBRIC_CODE = 9207;

    @Autowired
    public CalculateAbsenceDiscount(
            RubricService rubricService,
            AbsenceSheetService absenceSheetService,
            WorkShiftService workShiftService,
            HoursWorkedSheetService hoursWorkedSheetService,
            WorkingHoursConfig workingHoursConfig
    ) {
        super(workShiftService, hoursWorkedSheetService, workingHoursConfig);
        this.rubricService = rubricService;
        this.absenceSheetService = absenceSheetService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(param -> {
                List<AbsenceSheetResponseDTO> absenceSheets = getAbsenceWithoutJustification(param);

                Duration absenceDuration = absenceSheets.stream()
                    .filter(absenceSheet -> absenceSheet.getStatus().equals(ApprovalStatus.APPROVED))
                    .map(this::calculateAbsenceDuration)
                    .reduce(Duration::plus)
                    .orElse(Duration.ZERO);

                return new PayrollItemRequestDTO(
                    getRubricByCode(),
                    null,
                    BigDecimal.valueOf(absenceDuration.toHours()).multiply(BigDecimal.valueOf(60)),
                    calculateAbsenceDiscount(param),
                    BigDecimal.ZERO
                );
            })
            .orElse(null);
    }

    /**
     * Este método recupera uma rubrica pelo seu código.
     *
     * @return Um objeto RubricResponseDTO que representa a rubrica recuperada.
     */
    protected RubricResponseDTO getRubricByCode() {
        return rubricService.getByCode(RUBRIC_CODE);
    }

    /**
     * Este método recupera as faltas sem justificativa de um funcionário em um mês e ano específicos.
     *
     * @param parameters Os parâmetros de cálculo.
     * @return Uma lista de objetos AbsenceSheetResponseDTO que representam as faltas sem justificativa.
     */
    protected List<AbsenceSheetResponseDTO> getAbsenceWithoutJustification(CalculationParameters parameters) {
        return absenceSheetService.getAbsenceWithoutJustification(
            parameters.getEmployee().getId(),
            parameters.getMonth().getValue(),
            parameters.getYear()
        );
    }

    /**
     * Este método calcula a duração de uma ausência.
     *
     * @param absenceSheet A ausência.
     * @return A duração da ausência em horas.
     */
    protected Duration calculateAbsenceDuration(AbsenceSheetResponseDTO absenceSheet) {
        LocalDateTime startDateTime = absenceSheet.getStartDate();
        LocalDateTime endDateTime = absenceSheet.getEndDate();
        return Duration.between(startDateTime, endDateTime);
    }

    /**
     * Este método calcula o desconto por ausência de um funcionário em um mês.
     *
     * @param parameters Os parâmetros de cálculo.
     * @return O valor do desconto por ausência.
     */
    protected BigDecimal calculateAbsenceDiscount(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(param -> {
                List<AbsenceSheetResponseDTO> absenceSheets = getAbsenceWithoutJustification(param);
                return absenceSheets
                    .stream()
                    .map(absenceSheet -> {
                        BigDecimal absenceDiscount = BigDecimal.ZERO;
                        if (absenceSheet.getStatus().equals(ApprovalStatus.APPROVED)) {
                            // Calcula a duração da ausência
                            Duration absenceDuration = calculateAbsenceDuration(absenceSheet);
                            // Calcula o valor da hora do funcionário
                            BigDecimal employeeHourlyRate = EmployeeUtils.calculateHourlyRate(
                                param.getEmployee().getSalary(),
                                getWorkingHoursInMonth(parameters)
                            );
                            // Calcula o desconto de ausência
                            long absenceHours = absenceDuration.toHours();
                            absenceDiscount = BigDecimal.valueOf(absenceHours).multiply(employeeHourlyRate);
                        }
                        return absenceDiscount;
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            })
            .orElse(BigDecimal.ZERO);
    }
}