package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.AbsenceSheet;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.repository.AbsenceSheetRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.AbsenceType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.absence_sheet.AbsenceSheetRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.absence_sheet.AbsenceSheetResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.specification.AbsenceSheetSpecification;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class AbsenceSheetService extends AbstractService<AbsenceSheetRequestDTO, AbsenceSheetResponseDTO> {
    private final AbsenceSheetRepository repository;
    private final AbsenceSheetRequestMapper requestMapper;
    private final AbsenceSheetResponseMapper responseMapper;
    private final EmployeeService employeeService;

    private static final String ABSENCE_SHEET_EMPLOYEE_ID_VALIDATION_MESSAGE = "absenceSheetService.validation.employeeId";
    private static final String ABSENCE_SHEET_TYPE_VALIDATION_MESSAGE = "absenceSheetService.validation.type";
    private static final String ABSENCE_SHEET_START_DATE_VALIDATION_MESSAGE = "absenceSheetService.validation.startDate";
    private static final String ABSENCE_SHEET_END_DATE_VALIDATION_MESSAGE = "absenceSheetService.validation.endDate";

    @Autowired
    public AbsenceSheetService(
        AbsenceSheetRepository repository,
        AbsenceSheetRequestMapper requestMapper,
        AbsenceSheetResponseMapper responseMapper,
        EmployeeService employeeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<AbsenceSheetResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(absenceSheet -> responseMapper.toDTO(absenceSheet, AbsenceSheetResponseDTO.class))
            .toList();
    }

    @Override
    public AbsenceSheetResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchAbsenceSheetEntityById(id), AbsenceSheetResponseDTO.class);
    }

    public AbsenceSheet getEntityById(Integer id) {
        validate(id);
        return searchAbsenceSheetEntityById(id);
    }

    public List<AbsenceSheetResponseDTO> getAbsenceSheetsByEmployeeIdAndTypeAndDateRange(
        Integer employeeId,
        AbsenceType type,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {
        Employee employee = employeeService.getEmployeeEntityById(employeeId);
        AbsenceSheetSpecification spec = new AbsenceSheetSpecification(employee, type, startDate, endDate);
        return repository
            .findAll(spec)
            .stream()
            .map(absenceSheet -> responseMapper.toDTO(absenceSheet, AbsenceSheetResponseDTO.class))
            .toList();
    }

    @Override
    public AbsenceSheetResponseDTO create(AbsenceSheetRequestDTO request) {
        validate(request);
        AbsenceSheet absenceSheet = requestMapper.toEntity(request, AbsenceSheet.class);
        AbsenceSheet savedAbsenceSheet = repository.save(absenceSheet);
        return responseMapper.toDTO(savedAbsenceSheet, AbsenceSheetResponseDTO.class);
    }

    @Override
    public AbsenceSheetResponseDTO update(Integer id, AbsenceSheetRequestDTO request) {
        validate(id);
        validate(request);
        AbsenceSheet absenceSheet = searchAbsenceSheetEntityById(id);
        requestMapper.updateSourceFromDestination(absenceSheet, request);
        return responseMapper.toDTO(repository.save(absenceSheet), AbsenceSheetResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private AbsenceSheet searchAbsenceSheetEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(AbsenceSheetRequestDTO request) {
        super.validate(request);
        if (request.getEmployeeId() == null) throw new ResourceNotFoundException(getLocalizedMessage(ABSENCE_SHEET_EMPLOYEE_ID_VALIDATION_MESSAGE));
        if (request.getType() == null) throw new ResourceNotFoundException(getLocalizedMessage(ABSENCE_SHEET_TYPE_VALIDATION_MESSAGE));
        if (request.getStartDate() == null) throw new ResourceNotFoundException(getLocalizedMessage(ABSENCE_SHEET_START_DATE_VALIDATION_MESSAGE));
        if (request.getEndDate() == null) throw new ResourceNotFoundException(getLocalizedMessage(ABSENCE_SHEET_END_DATE_VALIDATION_MESSAGE));
        if (request.getStatus() == null) request.setStatus(ApprovalStatus.PENDING);
    }
}