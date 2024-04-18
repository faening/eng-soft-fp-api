package com.github.faening.eng_soft_fp_api.domain.calculation.income;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.enumeration.*;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.JobService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("SpellCheckingInspection")
public class CalculateDangerousnessAllowanceTest {
    @InjectMocks
    private CalculateDangerousnessAllowance calculateDangerousnessAllowance;

    @Mock
    private JobService jobService;

    @Mock
    private TaxOrValueService taxOrValueService;

    @Mock
    private RubricService rubricService;

    private EmployeeSummaryDTO employee;
    private JobResponseDTO job;
    private RubricResponseDTO rubric;
    private TaxOrValueResponseDTO taxOrValue;
    private List<TaxOrValueResponseDTO> taxOrValueList;
    private final static Integer RUBRIC_CODE = 1203;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        employee = new EmployeeSummaryDTO(
            1,
            "Carlos Eduardo Luís Peixoto",
            "176413030",
            "28263832217",
            LocalDate.of(1990, 1, 1),
            Gender.MALE,
            "Rua das Flores",
            "257",
            "Centro",
            "Próximo ao mercado",
            "Cascavel",
            BrazilianState.PR,
            "80000000",
            "41999998888",
            "carlos_eduardo_luis_peixoto@domain.com",
            LocalDate.of(2020, 1, 1),
            false,
            7,
            69,
            1,
            BigDecimal.valueOf(2000.00),
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        job = new JobResponseDTO(
            69,
            "Operador de Máquinas",
            ExperienceLevel.MID_LEVEL,
            BigDecimal.valueOf(2000.0),
            true,
            3,
            9,
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        rubric = new RubricResponseDTO(
            16,
            1203,
            "Adicional Periculosidade",
            "Adicional de periculosidade",
            "Adicional por serviços em condições perigosas",
            RubricType.INCOME,
            true,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        taxOrValue = new TaxOrValueResponseDTO(
            16,
            TaxOrValueType.DANGEROUSNESS_ALLOWANCE,
            null,
            null,
            null,
            null,
            BigDecimal.valueOf(30.0),
            "Percentual de adicional por periculosidade",
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        taxOrValueList = List.of(taxOrValue);
    }

    @Test
    public void testCalculate_WhenParametersAreCorrect_ReturnPayrollItemRequestDTO() {
        CalculationParameters parameters = new CalculationParameters(employee, Month.MARCH, 2024);

        Mockito.when(jobService.getById(employee.getJobId())).thenReturn(job);
        Mockito.when(rubricService.getByCode(RUBRIC_CODE)).thenReturn(rubric);
        Mockito.when(taxOrValueService.getByType(TaxOrValueType.DANGEROUSNESS_ALLOWANCE)).thenReturn(taxOrValueList);

        PayrollItemRequestDTO result = calculateDangerousnessAllowance.calculate(parameters);
        BigDecimal expected = BigDecimal.valueOf(600.00).setScale(2, RoundingMode.HALF_UP);

        assertEquals(rubric, result.getRubric());
        assertEquals(taxOrValue, result.getTaxOrValue());
        assertEquals(employee.getSalary(), result.getBaseValue());
        assertEquals(expected, result.getCalculatedValue());
    }

    @Test
    public void testCalculate_WhenParametersAreNull_ReturnNull() {
        CalculationParameters parameters = null;
        PayrollItemRequestDTO result = calculateDangerousnessAllowance.calculate(parameters);
        assertNull(result);
    }

    @Test
    public void testGetJobByEmployeeJobId_WhenJobExists_ReturnJob() {
        Mockito.when(jobService.getById(69)).thenReturn(job);
        JobResponseDTO result = calculateDangerousnessAllowance.getJobByEmployeeJobId(69);
        assertEquals(job, result);
    }

    @Test
    public void testGetRubricByCode_WhenRubricExists_ReturnRubric() {
        Mockito.when(rubricService.getByCode(RUBRIC_CODE)).thenReturn(rubric);
        assertEquals(rubric, calculateDangerousnessAllowance.getRubricByCode());
    }

    @Test
    public void testCalculateDangerousnessAllowance_WhenEmployeeAndTaxOrValueExists_ReturnCalculatedValue() {
        BigDecimal expected = BigDecimal.valueOf(600.00).setScale(2, RoundingMode.HALF_UP);
        BigDecimal result = calculateDangerousnessAllowance.calculateDangerousnessAllowance(employee);
        assertEquals(expected, result);
    }
}