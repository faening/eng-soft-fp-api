package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.WorkShift;
import com.github.faening.eng_soft_fp_api.data.repository.WorkShiftRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.work_shift.WorkShiftRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.work_shift.WorkShiftResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class WorkShiftService extends AbstractService<WorkShiftRequestDTO, WorkShiftResponseDTO> {
    private final WorkShiftRepository repository;
    private final WorkShiftRequestMapper requestMapper;
    private final WorkShiftResponseMapper responseMapper;

    private static final String WS_DESCRIPTION_VALIDATION_MESSAGE = "workShiftService.validation.description";
    private static final String WS_START_OF_WORKDAY_VALIDATION_MESSAGE = "workShiftService.validation.startOfWorkday";
    private static final String WS_START_OF_BREAK_VALIDATION_MESSAGE = "workShiftService.validation.startOfBreak";
    private static final String WS_END_OF_BREAK_VALIDATION_MESSAGE = "workShiftService.validation.endOfBreak";
    private static final String WS_END_OF_WORKDAY_VALIDATION_MESSAGE = "workShiftService.validation.endOfWorkday";
    private static final String WS_NIGHT_SHIFT_ALLOWANCE_VALIDATION_MESSAGE = "workShiftService.validation.nightShiftAllowance";

    @Autowired
    public WorkShiftService(
        WorkShiftRepository repository,
        WorkShiftRequestMapper requestMapper,
        WorkShiftResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<WorkShiftResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(workShift -> responseMapper.toDTO(workShift, WorkShiftResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public WorkShiftResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchWorkShiftById(id), WorkShiftResponseDTO.class);
    }

    public WorkShift getEntityById(Integer id) {
        validate(id);
        return searchWorkShiftById(id);
    }

    @Override
    public WorkShiftResponseDTO create(WorkShiftRequestDTO request) {
        validate(request);
        WorkShift workShift = requestMapper.toEntity(request, WorkShift.class);
        WorkShift savedWorkShift = repository.save(workShift);
        return responseMapper.toDTO(savedWorkShift, WorkShiftResponseDTO.class);
    }

    @Override
    public WorkShiftResponseDTO update(Integer id, WorkShiftRequestDTO request) {
        validate(id);
        validate(request);
        WorkShift workShift = searchWorkShiftById(id);
        requestMapper.updateSourceFromDestination(workShift, request);
        WorkShift savedWorkShift = repository.save(workShift);
        return responseMapper.toDTO(savedWorkShift, WorkShiftResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private WorkShift searchWorkShiftById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(WorkShiftRequestDTO workShiftRequestDTO) {
        if (workShiftRequestDTO.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage(WS_DESCRIPTION_VALIDATION_MESSAGE));
        if (workShiftRequestDTO.getStartOfWorkday() == null) throw new IllegalArgumentException(getLocalizedMessage(WS_START_OF_WORKDAY_VALIDATION_MESSAGE));
        if (workShiftRequestDTO.getStartOfBreak() == null) throw new IllegalArgumentException(getLocalizedMessage(WS_START_OF_BREAK_VALIDATION_MESSAGE));
        if (workShiftRequestDTO.getEndOfBreak() == null) throw new IllegalArgumentException(getLocalizedMessage(WS_END_OF_BREAK_VALIDATION_MESSAGE));
        if (workShiftRequestDTO.getEndOfWorkday() == null) throw new IllegalArgumentException(getLocalizedMessage(WS_END_OF_WORKDAY_VALIDATION_MESSAGE));
        if (workShiftRequestDTO.getNightShiftAllowance() == null) throw new IllegalArgumentException(getLocalizedMessage(WS_NIGHT_SHIFT_ALLOWANCE_VALIDATION_MESSAGE));
        if (workShiftRequestDTO.getEnabled() == null) workShiftRequestDTO.setEnabled(true);
    }
}