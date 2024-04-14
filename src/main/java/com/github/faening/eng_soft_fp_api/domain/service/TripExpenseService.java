package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.TripExpense;
import com.github.faening.eng_soft_fp_api.data.repository.TripExpenseRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.trip_expense.TripExpenseRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.trip_expense.TripExpenseResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.trip_expense.TripExpenseRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.trip_expense.TripExpenseResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class TripExpenseService extends AbstractService<TripExpenseRequestDTO, TripExpenseResponseDTO> {
    private final TripExpenseRepository repository;
    private final TripExpenseRequestMapper requestMapper;
    private final TripExpenseResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String TE_EMPLOYEE_ID_VALIDATION_MESSAGE = "tripExpenseService.validation.employeeId";
    private static final String TE_DATE_VALIDATION_MESSAGE = "tripExpenseService.validation.date";
    private static final String TE_AMOUNT_VALIDATION_MESSAGE = "tripExpenseService.validation.amount";

    @Autowired
    public TripExpenseService(
        TripExpenseRepository repository,
        TripExpenseRequestMapper requestMapper,
        TripExpenseResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<TripExpenseResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(tripExpense -> responseMapper.toDTO(tripExpense, TripExpenseResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public TripExpenseResponseDTO getById(Integer id) {
        return responseMapper.toDTO(searchTripExpenseEntityById(id), TripExpenseResponseDTO.class);
    }

    public TripExpense getEntityById(Integer id) {
        return searchTripExpenseEntityById(id);
    }

    public TripExpenseResponseDTO getByEmployeeIdAndDate(Integer employeeId, LocalDate date) {
        Employee employee = employeeService.getEntityById(employeeId);
        TripExpense tripExpense = repository.findByEmployeeAndDate(employee, date);
        return responseMapper.toDTO(tripExpense, TripExpenseResponseDTO.class);
    }

    @Override
    public TripExpenseResponseDTO create(TripExpenseRequestDTO request) {
        validate(request);
        TripExpense tripExpense = requestMapper.toEntity(request, TripExpense.class);
        TripExpense savedTripExpense = repository.save(tripExpense);
        return responseMapper.toDTO(savedTripExpense, TripExpenseResponseDTO.class);
    }

    @Override
    public TripExpenseResponseDTO update(Integer id, TripExpenseRequestDTO request) {
        validate(id);
        validate(request);
        TripExpense tripExpense = searchTripExpenseEntityById(id);
        requestMapper.updateSourceFromDestination(tripExpense, request);
        TripExpense updatedTripExpense = repository.save(tripExpense);
        return responseMapper.toDTO(updatedTripExpense, TripExpenseResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private TripExpense searchTripExpenseEntityById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE));
    }

    @Override
    protected void validate(TripExpenseRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(TE_EMPLOYEE_ID_VALIDATION_MESSAGE);
        if (request.getDate() == null) throw new IllegalArgumentException(TE_DATE_VALIDATION_MESSAGE);
        if (request.getAmount() == null) throw new IllegalArgumentException(TE_AMOUNT_VALIDATION_MESSAGE);
        if (request.getStatus() == null) request.setStatus(ApprovalStatus.PENDING);
    }
}