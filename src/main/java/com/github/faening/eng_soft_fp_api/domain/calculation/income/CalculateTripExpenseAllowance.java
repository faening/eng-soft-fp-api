package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TripExpenseService;
import com.github.faening.eng_soft_fp_api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateTripExpenseAllowance implements PayrollCalculation {
    private final RubricService rubricService;
    private final TripExpenseService tripExpenseService;
    private final static Integer RUBRIC_CODE = 1621;

    @Autowired
    public CalculateTripExpenseAllowance(
        RubricService rubricService,
        TripExpenseService tripExpenseService
    ) {
        this.rubricService = rubricService;
        this.tripExpenseService = tripExpenseService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(params -> new PayrollItemRequestDTO(
                getRubricByCode(),
                null,
                getTripExpenseAmount(parameters),
                getTripExpenseAmount(parameters),
                getTripExpenseAmount(parameters)
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
     * Este método recupera o valor total das despesas de viagem de um funcionário em um mês.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O valor total das despesas de viagem realizadas pelo funcionário no mês.
     */
    protected BigDecimal getTripExpenseAmount(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(params -> {
                LocalDate[] startAndEndDate = DateUtils.getFirstAndLastDayOfMonth(params.getYear(), params.getMonth().getValue());
                LocalDate startDate = startAndEndDate[0];
                LocalDate endDate = startAndEndDate[1];
                return tripExpenseService.getTotalTripExpenseAmountByEmployeeIdAndDateRange(params.getEmployee().getId(), startDate, endDate);
            })
            .orElse(BigDecimal.ZERO);
    }
}