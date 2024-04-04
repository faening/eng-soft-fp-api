package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.WorkShift;
import com.github.faening.eng_soft_fp_api.data.repository.WorkShiftRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.work_shift.WorkShiftMapper;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class WorkShiftService {
    private final WorkShiftRepository workShiftRepository;
    private final WorkShiftMapper workShiftMapper;

    @Autowired
    public WorkShiftService(WorkShiftRepository workShiftRepository, WorkShiftMapper workShiftMapper) {
        this.workShiftRepository = workShiftRepository;
        this.workShiftMapper = workShiftMapper;
    }

    private WorkShift searchWorkShiftById(Integer id) {
        return workShiftRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum turno de trabalho encontrado com o id: " + id)
        );
    }

    public List<WorkShiftDTO> getAllWorkShifts() {
        List<WorkShift> workShifts = workShiftRepository.findAll();
        return workShiftMapper.mapWorkShiftToWorkShiftDTO(workShifts);
    }

    public WorkShiftDTO getWorkShiftById(Integer id) {
        WorkShift workShift = searchWorkShiftById(id);
        return workShiftMapper.mapWorkShiftToWorkShiftDTO(workShift);
    }

    public WorkShiftDTO createWorkShift(WorkShiftDTO workShiftDTO) {
        validateWorkShiftDTO(workShiftDTO);
        WorkShift workShift = workShiftMapper.mapWorkShiftDTOToWorkShift(workShiftDTO);
        WorkShift createdWorkShift = workShiftRepository.save(workShift);
        return workShiftMapper.mapWorkShiftToWorkShiftDTO(createdWorkShift);
    }

    public WorkShiftDTO updateWorkShift(Integer id, WorkShiftDTO workShiftDTO) {
        validateWorkShiftDTO(workShiftDTO);
        WorkShift workShift = searchWorkShiftById(id);
        workShiftMapper.updateSourceFromDestination(workShift, workShiftDTO);
        WorkShift updatedWorkShift = workShiftRepository.save(workShift);
        return workShiftMapper.mapWorkShiftToWorkShiftDTO(updatedWorkShift);
    }

    public void deleteWorkShift(Integer id) {
        WorkShift workShift = searchWorkShiftById(id);
        workShiftRepository.delete(workShift);
    }

    private void validateWorkShiftDTO(WorkShiftDTO workShiftDTO) {
        if (workShiftDTO.getDescription() == null) throw new IllegalArgumentException("Descrição do turno de trabalho não pode ser nula");
        if (workShiftDTO.getStartOfWorkday() == null) throw new IllegalArgumentException("Início do turno de trabalho não pode ser nulo");
        if (workShiftDTO.getStartOfBreak() == null) throw new IllegalArgumentException("Início do intervalo do turno de trabalho não pode ser nulo");
        if (workShiftDTO.getEndOfBreak() == null) throw new IllegalArgumentException("Fim do intervalo do turno de trabalho não pode ser nulo");
        if (workShiftDTO.getEndOfWorkday() == null) throw new IllegalArgumentException("Fim do turno de trabalho não pode ser nulo");
        if (workShiftDTO.getNightShiftAllowance() == null) throw new IllegalArgumentException("Adicional noturno do turno de trabalho não pode ser nulo");
        if (workShiftDTO.getEnabled() == null) throw new IllegalArgumentException("Habilitado do turno de trabalho não pode ser nulo");
    }
}