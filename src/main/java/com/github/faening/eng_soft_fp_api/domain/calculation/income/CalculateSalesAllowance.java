package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.SaleService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import com.github.faening.eng_soft_fp_api.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateSalesAllowance implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final SaleService saleService;
    private final static Integer RUBRIC_CODE = 1207;

    @Autowired
    public CalculateSalesAllowance(
        RubricService rubricService,
        TaxOrValueService taxOrValueService,
        SaleService saleService
    ) {
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
        this.saleService = saleService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(emp -> new PayrollItemRequestDTO(
                getRubricByCode(),
                taxOrValueService.getSalesAllowanceBySaleRange(getSalesAmount(parameters)),
                getSalesAmount(parameters),
                calculateSalesAllowance(parameters),
                taxOrValueService.getSalesAllowanceBySaleRange(getSalesAmount(parameters)).getTaxPercentage()
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
     * Este método recupera o valor total das vendas realizadas por um funcionário em um mês.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O valor total das vendas realizadas pelo funcionário no mês.
     *
     */
    protected BigDecimal getSalesAmount(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(params -> {
                LocalDate[] startAndEndDate = DateUtils.getFirstAndLastDayOfMonth(params.getYear(), params.getMonth().getValue());
                LocalDate startDate = startAndEndDate[0];
                LocalDate endDate = startAndEndDate[1];
                return saleService.getTotalSalesAmountInDateRange(params.getEmployee().getId(), startDate, endDate);
            })
            .orElse(BigDecimal.ZERO);
    }

    /**
     * Este método calcula a comissão sobre as vendas realizadas por um funcionário em um mês.
     * O cálculo é feito multiplicando o valor total das vendas pelo percentual de comissão sobre o valor das vendas.
     *
     * @param parameters Parâmetros de cálculo que incluem detalhes do funcionário, do mês e ano de interesse.
     * @return O valor da comissão sobre as vendas realizadas pelo funcionário no mês.
     */
    protected BigDecimal calculateSalesAllowance(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(params -> {
                // Observe o valor total das vendas realizadas pelo funcionário no mês.
                BigDecimal salesAmount = getSalesAmount(params);
                // Recupere o percentual de comissão sobre o valor das vendas.
                BigDecimal allowancePercentage = taxOrValueService
                    .getSalesAllowanceBySaleRange(salesAmount).getTaxPercentage()
                    .divide(BigDecimal.valueOf(100), 3, RoundingMode.FLOOR);
                return salesAmount.multiply(allowancePercentage).setScale(2, RoundingMode.HALF_UP);
            })
            .orElse(BigDecimal.ZERO);
    }
}