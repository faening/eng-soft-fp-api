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

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class HoursWorkedSheetService extends AbstractService<HoursWorkedSheetRequestDTO, HoursWorkedSheetResponseDTO> {
    private final HoursWorkedSheetRepository hoursWorkedSheetRepository;
    private final HoursWorkedSheetRequestMapper hoursWorkedSheetRequestMapper;
    private final HoursWorkedSheetResponseMapper hoursWorkedSheetResponseMapper;
    private final EmployeeService employeeService;

    @Autowired
    public HoursWorkedSheetService(
        HoursWorkedSheetRepository hoursWorkedSheetRepository,
        HoursWorkedSheetRequestMapper hoursWorkedSheetRequestMapper,
        HoursWorkedSheetResponseMapper hoursWorkedSheetResponseMapper,
        EmployeeService employeeService
    ) {
        this.hoursWorkedSheetRepository = hoursWorkedSheetRepository;
        this.hoursWorkedSheetRequestMapper = hoursWorkedSheetRequestMapper;
        this.hoursWorkedSheetResponseMapper = hoursWorkedSheetResponseMapper;
        this.employeeService = employeeService;
    }

    @Override
    public List<HoursWorkedSheetResponseDTO> getAll() {
        return hoursWorkedSheetRepository
            .findAll()
            .stream()
            .map(hoursWorkedSheet -> hoursWorkedSheetResponseMapper.toDTO(hoursWorkedSheet, HoursWorkedSheetResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public HoursWorkedSheetResponseDTO getById(Integer id) {
        return hoursWorkedSheetResponseMapper.toDTO(searchHoursWorkedSheetById(id), HoursWorkedSheetResponseDTO.class);
    }

    @Override
    public HoursWorkedSheetResponseDTO create(HoursWorkedSheetRequestDTO request) {
        validateHoursWorkedSheetRequestDTO(request);
        HoursWorkedSheet hoursWorkedSheet = hoursWorkedSheetRequestMapper.toEntity(request, HoursWorkedSheet.class);
        HoursWorkedSheet savedHoursWorkedSheet = hoursWorkedSheetRepository.save(hoursWorkedSheet);
        return hoursWorkedSheetResponseMapper.toDTO(savedHoursWorkedSheet, HoursWorkedSheetResponseDTO.class);
    }

    @Override
    public HoursWorkedSheetResponseDTO update(Integer id, HoursWorkedSheetRequestDTO request) {
        validateId(id);
        validateHoursWorkedSheetRequestDTO(request);
        HoursWorkedSheet hoursWorkedSheet = searchHoursWorkedSheetById(id);
        hoursWorkedSheetRequestMapper.updateSourceFromDestination(hoursWorkedSheet, request);
        HoursWorkedSheet updatedHoursWorkedSheet = hoursWorkedSheetRepository.save(hoursWorkedSheet);
        return hoursWorkedSheetResponseMapper.toDTO(updatedHoursWorkedSheet, HoursWorkedSheetResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        HoursWorkedSheet hoursWorkedSheet = searchHoursWorkedSheetById(id);
        hoursWorkedSheetRepository.delete(hoursWorkedSheet);
    }

    /**
     * Busca as horas trabalhadas de um funcionário em um intervalo de datas.
     *
     * @param employeId Id do funcionário
     * @param startDate Data de início do intervalo
     * @param endDate   Data de fim do intervalo
     * @return Lista de horas trabalhadas
     */
    public List<HoursWorkedSheetResponseDTO> getWorkedHoursByEmployeeIdAndDateRange(Integer employeId, LocalDate startDate, LocalDate endDate) {
        Employee employee = employeeService.getEmployeeById(employeId);
        return hoursWorkedSheetRepository
            .findByEmployeeAndDateBetween(employee, startDate, endDate)
            .stream()
            .map(hoursWorkedSheet -> hoursWorkedSheetResponseMapper.toDTO(hoursWorkedSheet, HoursWorkedSheetResponseDTO.class))
            .collect(Collectors.toList());
    }

    private HoursWorkedSheet searchHoursWorkedSheetById(Integer id) {
        return hoursWorkedSheetRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum registro de horas trabalhadas encontrado com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage(getLocalizedMessage("hoursWorkedSheetService.validation.hoursWorkedSheetId")));
    }

    private void validateHoursWorkedSheetRequestDTO(HoursWorkedSheetRequestDTO hoursWorkedSheetRequestDTO) {
        if (hoursWorkedSheetRequestDTO.getEmployeeId() == null) throw new IllegalArgumentException(getLocalizedMessage("hoursWorkedSheetService.validation.employeeId"));
        if (hoursWorkedSheetRequestDTO.getDate() == null) throw new IllegalArgumentException(getLocalizedMessage("hoursWorkedSheetService.validation.date"));
    }
}