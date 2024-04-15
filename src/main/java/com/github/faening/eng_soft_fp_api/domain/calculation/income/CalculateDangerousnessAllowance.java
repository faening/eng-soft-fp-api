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

@SuppressWarnings("unused")
@Component
public class CalculateDangerousnessAllowance implements PayrollCalculation {
    private final JobService jobService;
    private final TaxOrValueService taxOrValueService;
    private final RubricService rubricService;
    private final static Integer RUBRIC_CODE = 1203;

    @Autowired
    public CalculateDangerousnessAllowance(
        JobService jobService,
        TaxOrValueService taxOrValueService,
        RubricService rubricService
    ) {
        this.jobService = jobService;
        this.taxOrValueService = taxOrValueService;
        this.rubricService = rubricService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        if (parameters != null) {
            JobResponseDTO job = getJobByEmployeeJobId(parameters.getEmployee().getJobId());

            if (job != null && job.getDangerousness()) {
                RubricResponseDTO rubric = getRubricByCode();
                TaxOrValueResponseDTO taxOrValue = getTaxOrValueByType();
                BigDecimal calculatedValue = calculateDangerousnessAllowance(parameters.getEmployee(), taxOrValue);
                return new PayrollItemRequestDTO(rubric, taxOrValue, parameters.getEmployee().getSalary(), calculatedValue);
            }
        }
        return null;
    }

    protected JobResponseDTO getJobByEmployeeJobId(Integer jobId) {
        return jobService.getById(jobId);
    }

    protected RubricResponseDTO getRubricByCode() {
        return rubricService.getByCode(RUBRIC_CODE);
    }

    protected TaxOrValueResponseDTO getTaxOrValueByType() {
        return taxOrValueService.getByType(TaxOrValueType.DANGEROUSNESS_ALLOWANCE).get(0);
    }

    @SuppressWarnings("BigDecimalMethodWithoutRoundingCalled")
    protected BigDecimal calculateDangerousnessAllowance(EmployeeSummaryDTO employee, TaxOrValueResponseDTO taxOrValue) {
        BigDecimal salary = employee.getSalary();
        BigDecimal taxPercentage = taxOrValue.getTaxPercentage().divide(BigDecimal.valueOf(100));
        return salary.multiply(taxPercentage);
    }
}