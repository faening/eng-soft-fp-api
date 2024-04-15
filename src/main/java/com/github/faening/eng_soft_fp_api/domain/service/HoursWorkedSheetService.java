package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.HoursWorkedSheet;
import com.github.faening.eng_soft_fp_api.data.repository.HoursWorkedSheetRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.hours_worked_sheet.HoursWorkedSheetRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.hours_worked_sheet.HoursWorkedSheetResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Service
public class HoursWorkedSheetService extends AbstractService<HoursWorkedSheetRequestDTO, HoursWorkedSheetResponseDTO> {
    private final HoursWorkedSheetRepository repository;
    private final HoursWorkedSheetRequestMapper requestMapper;
    private final HoursWorkedSheetResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String VALIDATION_MESSAGE_EMPLOYEE_ID = "hoursWorkedSheetService.validation.employeeId";
    private static final String VALIDATION_MESSAGE_DATE = "hoursWorkedSheetService.validation.date";

    @Autowired
    public HoursWorkedSheetService(
        HoursWorkedSheetRepository repository,
        HoursWorkedSheetRequestMapper requestMapper,
        HoursWorkedSheetResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<HoursWorkedSheetResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(hoursWorkedSheet -> responseMapper.toDTO(hoursWorkedSheet, HoursWorkedSheetResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public HoursWorkedSheetResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchHoursWorkedSheetEntityById(id), HoursWorkedSheetResponseDTO.class);
    }

    public HoursWorkedSheet getEntityById(Integer id) {
        validate(id);
        return searchHoursWorkedSheetEntityById(id);
    }

    public List<HoursWorkedSheetResponseDTO> getWorkedHoursByEmployeeIdAndDateRange(Integer employeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeService.getEntityById(employeId);
        return repository
            .findByEmployeeAndDateBetween(employee, startDate, endDate)
            .stream()
            .map(hoursWorkedSheet -> responseMapper.toDTO(hoursWorkedSheet, HoursWorkedSheetResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public HoursWorkedSheetResponseDTO create(HoursWorkedSheetRequestDTO request) {
        validate(request);
        HoursWorkedSheet hoursWorkedSheet = requestMapper.toEntity(request, HoursWorkedSheet.class);
        HoursWorkedSheet savedHoursWorkedSheet = repository.save(hoursWorkedSheet);
        return responseMapper.toDTO(savedHoursWorkedSheet, HoursWorkedSheetResponseDTO.class);
    }

    @Override
    public HoursWorkedSheetResponseDTO update(Integer id, HoursWorkedSheetRequestDTO request) {
        validate(id);
        validate(request);
        HoursWorkedSheet hoursWorkedSheet = searchHoursWorkedSheetEntityById(id);
        requestMapper.updateSourceFromDestination(hoursWorkedSheet, request);
        HoursWorkedSheet updatedHoursWorkedSheet = repository.save(hoursWorkedSheet);
        return responseMapper.toDTO(updatedHoursWorkedSheet, HoursWorkedSheetResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private HoursWorkedSheet searchHoursWorkedSheetEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(HoursWorkedSheetRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_EMPLOYEE_ID));
        if (request.getDate() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_DATE));
    }
}