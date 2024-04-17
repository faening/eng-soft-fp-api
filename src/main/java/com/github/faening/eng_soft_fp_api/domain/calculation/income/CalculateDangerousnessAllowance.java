package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
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
public class CalculateDangerousnessAllowance implements PayrollCalculation {
    private final RubricService rubricService;
    private final JobService jobService;
    private final TaxOrValueService taxOrValueService;
    private final static Integer RUBRIC_CODE = 1203;

    @Autowired
    public CalculateDangerousnessAllowance(
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
            .filter(job -> job.getDangerousness() != null && job.getDangerousness())
            .map(job -> {
                RubricResponseDTO rubric = getRubricByCode();
                TaxOrValueResponseDTO taxOrValue = getTaxOrValueByType();
                BigDecimal calculatedValue = calculateDangerousnessAllowance(parameters.getEmployee(), taxOrValue);

                return new PayrollItemRequestDTO(
                    rubric,
                    taxOrValue,
                    parameters.getEmployee().getSalary(),
                    calculatedValue,
                    taxOrValue.getTaxPercentage()
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
     * Este método recupera um trabalho pelo seu ID.
     *
     * @param jobId O ID do trabalho do funcionário.
     * @return Um objeto JobResponseDTO que representa o trabalho recuperado. Retorna null se o trabalho não for encontrado.
     */
    protected JobResponseDTO getJobByEmployeeJobId(Integer jobId) {
        return jobService.getById(jobId);
    }

    /**
     * Este método recupera o primeiro imposto ou valor pelo seu tipo.
     *
     * @return Um objeto TaxOrValueResponseDTO que representa o imposto ou valor recuperado. Retorna null se o imposto ou valor não for encontrado.
     */
    protected TaxOrValueResponseDTO getTaxOrValueByType() {
        return taxOrValueService.getByType(TaxOrValueType.DANGEROUSNESS_ALLOWANCE).get(0);
    }

    /**
     * Este método calcula a periculosidade do salário de um funcionário.
     *
     * @param employee Um objeto EmployeeSummaryDTO que representa o funcionário.
     * @param taxOrValue Um objeto TaxOrValueResponseDTO que representa o imposto ou valor.
     * @return O valor calculado da periculosidade do salário do funcionário.
     */
    protected BigDecimal calculateDangerousnessAllowance(EmployeeSummaryDTO employee, TaxOrValueResponseDTO taxOrValue) {
        return Optional.ofNullable(employee)
            .map(EmployeeSummaryDTO::getSalary)
            .flatMap(salary -> Optional.ofNullable(taxOrValue)
                .map(tax -> tax.getTaxPercentage().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
                .map(salary::multiply))
            .orElse(BigDecimal.ZERO);
    }
}