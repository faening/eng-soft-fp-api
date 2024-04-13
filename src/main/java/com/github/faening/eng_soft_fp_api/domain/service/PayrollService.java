package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Payroll;
import com.github.faening.eng_soft_fp_api.data.repository.PayrollRepository;
import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.payroll.PayrollRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.payroll.PayrollResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class PayrollService extends AbstractService<PayrollRequestDTO, PayrollResponseDTO> {
    private final PayrollRepository repository;
    private final PayrollRequestMapper requestMapper;
    private final PayrollResponseMapper responseMapper;
    private final EmployeeService employeeService;
    private final List<PayrollCalculation> payrollCalculations;

    private final static String PAYROLL_DELETE_VALIDATION_MESSAGE = "payrollService.validation.delete";

    @Autowired
    public PayrollService(
        PayrollRepository repository,
        PayrollRequestMapper requestMapper,
        PayrollResponseMapper responseMapper,
        EmployeeService employeeService,
        List<PayrollCalculation> payrollCalculations
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
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
        return responseMapper.toDTO(repository.findByEmployeeAndMonthAndYear(employee, month, year), PayrollResponseDTO.class);
    }

    @Override
    public PayrollResponseDTO create(PayrollRequestDTO request) {
        return null;
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
            throw new RuntimeException(PAYROLL_DELETE_VALIDATION_MESSAGE);
        }
    }

    private Payroll searchPayrollById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new RuntimeException(ID_VALIDATION_MESSAGE)
        );
    }

    public PayrollResponseDTO calculate(Integer employeeId, Month month, Integer year) {
        validateCalculationParameters(employeeId, month, year);
        // Verificar se a folha de pagamento já foi calculada e fechada. Se sim, retornar a folha de pagamento e os itens. Se não, continuar.
        List<PayrollItemRequestDTO> payrollItems = getPayrollItems(employeeId, month, year);
        // Ordenar os itens de folha de pagamento por: PROVENTOS, DESCONTOS, NEUTROS
        // Calcular o totais da folha de pagamento
        // Salvar a folha de pagamento no banco de dados
        // Retornar a folha de pagamento e os itens
        return null;
    }

    private void validateCalculationParameters(Integer employeeId, Month month, Integer year) {
        validate(employeeId);
        // Validate Month
        validate(year);
    }

    private List<PayrollItemRequestDTO> getPayrollItems(Integer employeeId, Month month, Integer year) {
        CalculationParameters parameters = new CalculationParameters(employeeService.getSummaryById(employeeId), month, year);
        List<PayrollItemRequestDTO> items = new ArrayList<>();
        for (PayrollCalculation calculation : payrollCalculations) {
            PayrollItemRequestDTO item = calculation.calculate(parameters);
            if (item != null) { items.add(item); }
        }
        return items;
    }
}