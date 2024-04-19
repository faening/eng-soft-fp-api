package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.JobService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateUnhealthinessAllowance implements PayrollCalculation {
    private final RubricService rubricService;
    private final JobService jobService;
    private final TaxOrValueService taxOrValueService;
    private final static Integer RUBRIC_CODE = 1202;

    @Autowired
    public CalculateUnhealthinessAllowance(
        RubricService rubricService,
        JobService jobService,
        TaxOrValueService taxOrValueService
    ) {
        this.rubricService = rubricService;
        this.jobService = jobService;
        this.taxOrValueService = taxOrValueService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .map(EmployeeSummaryDTO::getJobId)
            .map(this::getJobByEmployeeJobId)
            .filter(job -> job.getUnhealthiness() != null && job.getUnhealthiness() > 0)
            .map(job -> {
                TaxOrValueResponseDTO allowancePercentage = taxOrValueService.getUnhealthinessAllowanceByRangeId(job.getUnhealthiness());
                BigDecimal calculatedValue = calculateUnhealthinessAllowance(allowancePercentage);

                return new PayrollItemRequestDTO(
                    getRubricByCode(),
                    allowancePercentage,
                    taxOrValueService.getMinimumWage(),
                    calculatedValue,
                    allowancePercentage.getTaxPercentage());
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
     * Este método recupera um trabalho pelo seu ID.
     *
     * @param jobId O ID do trabalho do funcionário.
     * @return Um objeto JobResponseDTO que representa o trabalho recuperado. Retorna null se o trabalho não for encontrado.
     */
    protected JobResponseDTO getJobByEmployeeJobId(Integer jobId) {
        return jobService.getById(jobId);
    }

    /**
     * Este método calcula o adicional por insalubridade com base no percentual aplicável e no salário mínimo nacional.
     *
     * @param taxOrValue Um objeto TaxOrValueResponseDTO que representa o percentual de adicional por insalubridade.
     * @return O valor calculado do adicional por insalubridade. Se o percentual não for fornecido, retorna 0.
     */
    protected BigDecimal calculateUnhealthinessAllowance(TaxOrValueResponseDTO taxOrValue) {
        return Optional.ofNullable(taxOrValue)
            .map(tax -> tax.getTaxPercentage().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
            .orElse(BigDecimal.ZERO)
            .multiply(taxOrValueService.getMinimumWage())
            .setScale(2, RoundingMode.HALF_UP);
    }
}