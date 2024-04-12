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
    private final AbsenceSheetRepository absenceSheetRepository;
    private final AbsenceSheetRequestMapper absenceSheetRequestMapper;
    private final AbsenceSheetResponseMapper absenceSheetResponseMapper;
    private final EmployeeService employeeService;

    @Autowired
    public AbsenceSheetService(
        AbsenceSheetRepository absenceSheetRepository,
        AbsenceSheetRequestMapper absenceSheetRequestMapper,
        AbsenceSheetResponseMapper absenceSheetResponseMapper,
        EmployeeService employeeService
    ) {
        this.absenceSheetRepository = absenceSheetRepository;
        this.absenceSheetRequestMapper = absenceSheetRequestMapper;
        this.absenceSheetResponseMapper = absenceSheetResponseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<AbsenceSheetResponseDTO> getAll() {
        return absenceSheetRepository
            .findAll()
            .stream()
            .map(absenceSheet -> absenceSheetResponseMapper.toDTO(absenceSheet, AbsenceSheetResponseDTO.class))
            .toList();
    }

    @Override
    public AbsenceSheetResponseDTO getById(Integer id) {
        validateId(id);
        return absenceSheetResponseMapper.toDTO(searchAbsenceSheetById(id), AbsenceSheetResponseDTO.class);
    }

    @Override
    public AbsenceSheetResponseDTO create(AbsenceSheetRequestDTO request) {
        validateAbsenceSheetRequestDTO(request);
        AbsenceSheet absenceSheet = absenceSheetRequestMapper.toEntity(request, AbsenceSheet.class);
        AbsenceSheet savedAbsenceSheet = absenceSheetRepository.save(absenceSheet);
        return absenceSheetResponseMapper.toDTO(savedAbsenceSheet, AbsenceSheetResponseDTO.class);
    }

    @Override
    public AbsenceSheetResponseDTO update(Integer id, AbsenceSheetRequestDTO request) {
        validateId(id);
        validateAbsenceSheetRequestDTO(request);
        AbsenceSheet absenceSheet = searchAbsenceSheetById(id);
        absenceSheetRequestMapper.updateSourceFromDestination(absenceSheet, request);
        return absenceSheetResponseMapper.toDTO(absenceSheetRepository.save(absenceSheet), AbsenceSheetResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        absenceSheetRepository.deleteById(id);
    }

    public AbsenceSheet searchAbsenceSheetById(Integer id) {
        return absenceSheetRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum relatório de ausências encontrado com o ID: " + id)
        );
    }

    /**
     * Busca as folhas de ausência de um funcionário em um intervalo de datas.
     *
     * @param employeeId Id do funcionário
     * @param type (opcional) Tipo de ausência
     * @param startDate (opcional) Data de início do intervalo
     * @param endDate (opcional) Data de fim do intervalo
     * @return Lista de folhas de ausência
    */
    public List<AbsenceSheetResponseDTO> getAbsenceSheetsByEmployeeIdAndTypeAndDateRange(Integer employeeId, AbsenceType type, LocalDateTime startDate, LocalDateTime endDate) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        AbsenceSheetSpecification spec = new AbsenceSheetSpecification(employee, type, startDate, endDate);
        return absenceSheetRepository
            .findAll(spec)
            .stream()
            .map(absenceSheet -> absenceSheetResponseMapper.toDTO(absenceSheet, AbsenceSheetResponseDTO.class))
            .toList();
    }

    private void validateId(Integer id) {
        if (id == null) throw new ResourceNotFoundException(getLocalizedMessage("absenceSheetService.validation.absenceSheetId"));
    }

    private void validateAbsenceSheetRequestDTO(AbsenceSheetRequestDTO request) {
        if (request.getEmployeeId() == null) throw new ResourceNotFoundException(getLocalizedMessage("absenceSheetService.validation.employeeId"));
        if (request.getType() == null) throw new ResourceNotFoundException(getLocalizedMessage("absenceSheetService.validation.type"));
        if (request.getStartDate() == null) throw new ResourceNotFoundException(getLocalizedMessage("absenceSheetService.validation.startDate"));
        if (request.getEndDate() == null) throw new ResourceNotFoundException(getLocalizedMessage("absenceSheetService.validation.endDate"));
        if (request.getStatus() == null) request.setStatus(ApprovalStatus.PENDING);
    }
}