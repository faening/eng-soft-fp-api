package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeDependentService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateFamilyAllowance implements PayrollCalculation {
    private final RubricService rubricService;
    private final TaxOrValueService taxOrValueService;
    private final EmployeeDependentService employeeDependentService;
    private final static Integer RUBRIC_CODE = 1409;

    @Autowired
    public CalculateFamilyAllowance(
        RubricService rubricService,
        TaxOrValueService taxOrValueService,
        EmployeeDependentService employeeDependentService
    ) {
        this.rubricService = rubricService;
        this.taxOrValueService = taxOrValueService;
        this.employeeDependentService = employeeDependentService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(CalculationParameters::getEmployee)
            .filter(employee -> getDependentsByEmployeeWithFamilyAllowance(employee) > 0)
            .map(employee -> {
                return new PayrollItemRequestDTO(
                    getRubricByCode(),
                    getTaxOrValueByType(),
                    getMinimumWage().getFixedValue(),
                    calculateFamilyAllowance(parameters.getEmployee()),
                    BigDecimal.valueOf(getDependentsByEmployeeWithFamilyAllowance(parameters.getEmployee()))
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
     * Este método recupera o primeiro imposto ou valor pelo seu tipo.
     *
     * @return Um objeto TaxOrValueResponseDTO que representa o imposto ou valor recuperado. Retorna null se o imposto ou valor não for encontrado.
     */
    protected TaxOrValueResponseDTO getTaxOrValueByType() {
        return taxOrValueService.getByType(TaxOrValueType.FAMILY_ALLOWANCE).get(0);
    }

    protected TaxOrValueResponseDTO getMinimumWage() {
        return taxOrValueService.getByType(TaxOrValueType.MINIMUM_WAGE).get(0);
    }

    /**
     * Este método recupera a quantidade de dependentes de um funcionário que possuem direito ao auxílio família.
     *
     * @param employee O funcionário que terá a quantidade de dependentes com direito ao auxílio família recuperada.
     * @return A quantidade de dependentes do funcionário que possuem direito ao auxílio família.
     */
    protected Integer getDependentsByEmployeeWithFamilyAllowance(EmployeeSummaryDTO employee) {
        long count = employeeDependentService.getByEmployeeId(employee.getId())
            .stream()
            .filter(EmployeeDependentResponseDTO::getFamilyAllowance)
            .count();
        return Math.toIntExact(count);
    }

    /**
     * Este método calcula o auxílio família de um funcionário com base no salário mínimo e na quantidade de dependentes com direito ao auxílio família.
     *
     * @param employee O funcionário que terá o auxílio família calculado.
     * @return O valor calculado do auxílio família do funcionário.
     */
    protected BigDecimal calculateFamilyAllowance(EmployeeSummaryDTO employee) {
        return Optional.ofNullable(employee)
            .map(emp -> {
                BigDecimal minimumWageValue = getMinimumWage().getFixedValue();
                BigDecimal familyAllowancePercentage = getTaxOrValueByType().getTaxPercentage().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
                return minimumWageValue
                    .multiply(BigDecimal.valueOf(getDependentsByEmployeeWithFamilyAllowance(emp)))
                    .multiply(familyAllowancePercentage);
            })
            .map(value -> value.setScale(2, RoundingMode.HALF_UP))
            .orElse(BigDecimal.ZERO);
    }
}