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

@SuppressWarnings("unused")
@Service
public class WorkShiftService extends AbstractService<WorkShiftRequestDTO, WorkShiftResponseDTO> {
    private final WorkShiftRepository repository;
    private final WorkShiftRequestMapper requestMapper;
    private final WorkShiftResponseMapper responseMapper;

    private static final String VALIDATION_MESSAGE_DESCRIPTION = "workShiftService.validation.description";
    private static final String VALIDATION_MESSAGE_START_OF_WORKDAY = "workShiftService.validation.startOfWorkday";
    private static final String VALIDATION_MESSAGE_START_OF_BREAK = "workShiftService.validation.startOfBreak";
    private static final String VALIDATION_MESSAGE_END_OF_BREAK = "workShiftService.validation.endOfBreak";
    private static final String VALIDATION_MESSAGE_END_OF_WORKDAY = "workShiftService.validation.endOfWorkday";

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
    protected void validate(WorkShiftRequestDTO request) {
        super.validate(request);
        if (request.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_DESCRIPTION));
        if (request.getStartOfWorkday() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_START_OF_WORKDAY));
        if (request.getStartOfBreak() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_START_OF_BREAK));
        if (request.getEndOfBreak() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_END_OF_BREAK));
        if (request.getEndOfWorkday() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_END_OF_WORKDAY));
        if (request.getReducedShift() == null) request.setReducedShift(false);
        if (request.getNightShiftAllowance() == null) request.setNightShiftAllowance(false);
        if (request.getEnabled() == null) request.setEnabled(true);
    }
}