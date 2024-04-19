package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Payroll;
import com.github.faening.eng_soft_fp_api.data.repository.PayrollRepository;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.enumeration.RubricType;
import com.github.faening.eng_soft_fp_api.domain.mapper.payroll.PayrollRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.payroll.PayrollResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class PayrollService extends AbstractService<PayrollRequestDTO, PayrollResponseDTO> {
    private final PayrollRepository repository;
    private final PayrollRequestMapper requestMapper;
    private final PayrollResponseMapper responseMapper;
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final List<PayrollCalculation> payrollCalculations;

    private final static Integer INSS_RUBRIC_CODE = 9201;
    private final static Integer IRRF_RUBRIC_CODE = 9203;

    private final static String VALIDATION_MESSAGE_DELETE = "payrollService.validation.delete";

    @Autowired
    public PayrollService(
        PayrollRepository repository,
        PayrollRequestMapper requestMapper,
        PayrollResponseMapper responseMapper,
        EmployeeService employeeService,
        CompanyService companyService,
        List<PayrollCalculation> payrollCalculations
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.payrollCalculations = payrollCalculations;
    }

    @Override
    public List<PayrollResponseDTO> getAll() {
        return null;
    }

    @Override
    public PayrollResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchPayrollById(id), PayrollResponseDTO.class);
    }

    public PayrollResponseDTO getByEmployeeIdAndMonthAndYear(Integer employeeId, Month month, Integer year) {
        validate(employeeId);
        Employee employee = employeeService.getEntityById(employeeId);
        Payroll payroll = repository.findByEmployeeAndMonthAndYear(employee, month, year);
        return payroll == null ? null : responseMapper.toDTO(payroll, PayrollResponseDTO.class);
    }

    @Override
    public PayrollResponseDTO create(PayrollRequestDTO request) {
        Payroll payroll = requestMapper.toEntity(request, Payroll.class);
        payroll.getItems().forEach(item -> item.setPayroll(payroll));
        Payroll savedPayroll = repository.save(payroll);
        return responseMapper.toDTO(savedPayroll, PayrollResponseDTO.class);
    }

    @Override
    public PayrollResponseDTO update(Integer id, PayrollRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        Payroll payroll = searchPayrollById(id);
        if (payroll.getStatus() == PaymentStatus.PENDING) {
            repository.delete(payroll);
        } else {
            throw new RuntimeException(VALIDATION_MESSAGE_DELETE);
        }
    }

    private Payroll searchPayrollById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException(ID_VALIDATION_MESSAGE)
        );
    }

    public PayrollResponseDTO calculate(Integer employeeId, Month month, Integer year) {
        validateCalculationParameters(employeeId, month, year);
        CalculationParameters parameters = new CalculationParameters(getEmployeeSummaryById(employeeId), month, year);

        if (!checkPayrollStatus(parameters)) {
            List<PayrollItemRequestDTO> payrollItems = getPayrollItems(parameters);
            EmployeeSummaryDTO employee = getEmployeeSummaryById(parameters.getEmployee().getId());

            AtomicReference<BigDecimal> totalAdditions = new AtomicReference<>(BigDecimal.ZERO);
            AtomicReference<BigDecimal> totalDiscounts = new AtomicReference<>(BigDecimal.ZERO);

            payrollItems.forEach(item -> {
                if (item.getRubric().getType().equals(RubricType.INCOME)) {
                    totalAdditions.set(totalAdditions.get().add(item.getCalculatedValue()));
                } else if (item.getRubric().getType().equals(RubricType.DEDUCTION)) {
                    totalDiscounts.set(totalDiscounts.get().add(item.getCalculatedValue()));
                }
            });

            BigDecimal totalLiquid = totalAdditions.get().subtract(totalDiscounts.get());

            // Apurar os totais de INSS
            PayrollItemRequestDTO inssItem = payrollItems.stream()
                .filter(item -> item.getRubric().getCode().equals(INSS_RUBRIC_CODE))
                .findFirst()
                .orElse(null);

            // Apurar os totais de IRRF
            PayrollItemRequestDTO irrfItem = payrollItems.stream()
                .filter(item -> item.getRubric().getCode().equals(IRRF_RUBRIC_CODE))
                .findFirst()
                .orElse(null);

            assert inssItem != null;
            assert irrfItem != null;
            PayrollRequestDTO payrollRequest = new PayrollRequestDTO(
                1,
                employee.getId(),
                parameters.getMonth(),
                parameters.getYear(),
                employee.getSalary(),
                inssItem.getBaseValue(),
                inssItem.getReference(),
                irrfItem.getBaseValue(),
                irrfItem.getReference(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.valueOf(totalAdditions.get().doubleValue()).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(totalDiscounts.get().doubleValue()).setScale(2, RoundingMode.HALF_UP),
                totalLiquid,
                payrollItems,
                PaymentStatus.RELEASED,
                null
            );

            return create(payrollRequest);
        }

        // Se a folha de pagamento já foi aprovada, paga ou cancelada, retornar a folha de pagamento
        return getByEmployeeIdAndMonthAndYear(
            parameters.getEmployee().getId(),
            parameters.getMonth(),
            parameters.getYear());
    }

    private void validateCalculationParameters(Integer employeeId, Month month, Integer year) {
        validate(employeeId);
        // Validate Month
        validate(year);
    }

    /**
     * Este método verifica se a folha de pagamento já foi aprovada, paga ou cancelada.
     *
     * @param parameters Parâmetros de cálculo da folha de pagamento
     * @return true se a folha de pagamento já foi aprovada, paga ou cancelada, false caso contrário
     */
    private Boolean checkPayrollStatus(CalculationParameters parameters) {
        PayrollResponseDTO payroll = getByEmployeeIdAndMonthAndYear(
            parameters.getEmployee().getId(),
            parameters.getMonth(),
            parameters.getYear());

        return payroll != null &&
               payroll.getStatus() != PaymentStatus.PENDING &&
               payroll.getStatus() != PaymentStatus.DENIED &&
               payroll.getStatus() != PaymentStatus.CANCELED;
    }

    /**
     * Este método recupera os itens de folha de pagamento a partir da lista de cálculos de folha de pagamento.
     *
     * @param parameters Parâmetros de cálculo da folha de pagamento
     * @return Lista de itens de folha de pagamento
     */
    private List<PayrollItemRequestDTO> getPayrollItems(CalculationParameters parameters) {
        EmployeeSummaryDTO employee = getEmployeeSummaryById(parameters.getEmployee().getId());
        List<PayrollItemRequestDTO> items = new ArrayList<>();

        for (PayrollCalculation calculation : payrollCalculations) {
            PayrollItemRequestDTO item = calculation.calculate(parameters);
            if (item != null) { items.add(item); }
        }

        // Ordenar os itens de folha de pagamento por: PROVENTOS, DESCONTOS, NEUTROS
        items.sort(Comparator.comparing(item -> item.getRubric().getType()));

        return items;
    }

    private EmployeeSummaryDTO getEmployeeSummaryById(Integer employeeId) {
        return employeeService.getSummaryById(employeeId);
    }
}