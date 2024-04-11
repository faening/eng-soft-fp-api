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
    private final WorkShiftRepository workShiftRepository;
    private final WorkShiftRequestMapper workShiftRequestMapper;
    private final WorkShiftResponseMapper workShiftResponseMapper;

    @Autowired
    public WorkShiftService(
        WorkShiftRepository workShiftRepository,
        WorkShiftRequestMapper workShiftRequestMapper,
        WorkShiftResponseMapper workShiftResponseMapper
    ) {
        this.workShiftRepository = workShiftRepository;
        this.workShiftRequestMapper = workShiftRequestMapper;
        this.workShiftResponseMapper = workShiftResponseMapper;
    }

    @Override
    public List<WorkShiftResponseDTO> getAll() {
        return workShiftRepository
            .findAll()
            .stream()
            .map(workShift -> workShiftResponseMapper.toDTO(workShift, WorkShiftResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public WorkShiftResponseDTO getById(Integer id) {
        validateId(id);
        return workShiftResponseMapper.toDTO(searchWorkShiftById(id), WorkShiftResponseDTO.class);
    }

    @Override
    public WorkShiftResponseDTO create(WorkShiftRequestDTO request) {
        validateWorkShiftDTO(request);
        WorkShift workShift = workShiftRequestMapper.toEntity(request, WorkShift.class);
        WorkShift savedWorkShift = workShiftRepository.save(workShift);
        return workShiftResponseMapper.toDTO(savedWorkShift, WorkShiftResponseDTO.class);
    }

    @Override
    public WorkShiftResponseDTO update(Integer id, WorkShiftRequestDTO request) {
        validateId(id);
        validateWorkShiftDTO(request);
        WorkShift workShift = searchWorkShiftById(id);
        workShiftRequestMapper.updateSourceFromDestination(workShift, request);
        WorkShift savedWorkShift = workShiftRepository.save(workShift);
        return workShiftResponseMapper.toDTO(savedWorkShift, WorkShiftResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        workShiftRepository.deleteById(id);
    }

    private WorkShift searchWorkShiftById(Integer id) {
        return workShiftRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum turno de trabalho encontrado com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.workShiftId"));
    }

    private void validateWorkShiftDTO(WorkShiftRequestDTO workShiftRequestDTO) {
        if (workShiftRequestDTO.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.description"));
        if (workShiftRequestDTO.getStartOfWorkday() == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.startOfWorkday"));
        if (workShiftRequestDTO.getStartOfBreak() == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.startOfBreak"));
        if (workShiftRequestDTO.getEndOfBreak() == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.endOfBreak"));
        if (workShiftRequestDTO.getEndOfWorkday() == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.endOfWorkday"));
        if (workShiftRequestDTO.getNightShiftAllowance() == null) throw new IllegalArgumentException(getLocalizedMessage("workShiftService.validation.nightShiftAllowance"));
        if (workShiftRequestDTO.getEnabled() == null) workShiftRequestDTO.setEnabled(true);
    }
}