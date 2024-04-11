package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.HoursWorkedSheet;
import com.github.faening.eng_soft_fp_api.data.repository.HoursWorkedSheetRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee.EmployeeSummaryMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.hours_worked_sheet.HoursWorkedSheetRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.hours_worked_sheet.HoursWorkedSheetResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
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
public class HoursWorkedSheetService {
    private final HoursWorkedSheetRepository hoursWorkedSheetRepository;
    private final HoursWorkedSheetRequestMapper hoursWorkedSheetRequestMapper;
    private final HoursWorkedSheetResponseMapper hoursWorkedSheetResponseMapper;
    private final EmployeeSummaryMapper employeeSummaryMapper;

    @Autowired
    public HoursWorkedSheetService(
        HoursWorkedSheetRepository hoursWorkedSheetRepository,
        HoursWorkedSheetRequestMapper hoursWorkedSheetRequestMapper,
        HoursWorkedSheetResponseMapper hoursWorkedSheetResponseMapper,
        EmployeeSummaryMapper employeeSummaryMapper
    ) {
        this.hoursWorkedSheetRepository = hoursWorkedSheetRepository;
        this.hoursWorkedSheetRequestMapper = hoursWorkedSheetRequestMapper;
        this.hoursWorkedSheetResponseMapper = hoursWorkedSheetResponseMapper;
        this.employeeSummaryMapper = employeeSummaryMapper;
    }

    private HoursWorkedSheet searchHoursWorkedSheetById(Integer id) {
        return hoursWorkedSheetRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum registro de horas trabalhadas encontrado com o id: " + id)
        );
    }

    /**
     * Busca as horas trabalhadas de um funcionário em um intervalo de datas.
     *
     * @param employeeSummaryDTO DTO com o resumo do funcionário
     * @param startDate Data de início do intervalo
     * @param endDate Data de fim do intervalo
     *
     * @return Lista de horas trabalhadas
     */
    public List<HoursWorkedSheetResponseDTO> getWorkedHoursByEmployeeAndDateRange(EmployeeSummaryDTO employeeSummaryDTO, LocalDate startDate, LocalDate endDate) {
        return hoursWorkedSheetRepository
            .findByEmployeeAndDateBetween(employeeSummaryMapper.toEntity(employeeSummaryDTO, Employee.class), startDate, endDate)
            .stream()
            .map(hoursWorkedSheet -> hoursWorkedSheetResponseMapper.toDTO(hoursWorkedSheet, HoursWorkedSheetResponseDTO.class))
            .collect(Collectors.toList());
    }

    public HoursWorkedSheetResponseDTO createHoursWorkedSheet(HoursWorkedSheetRequestDTO hoursWorkedSheetRequestDTO) {
        HoursWorkedSheet hoursWorkedSheet = hoursWorkedSheetRequestMapper.toEntity(hoursWorkedSheetRequestDTO, HoursWorkedSheet.class);
        HoursWorkedSheet createdHoursWorkedSheet = hoursWorkedSheetRepository.save(hoursWorkedSheet);
        return hoursWorkedSheetResponseMapper.toDTO(createdHoursWorkedSheet, HoursWorkedSheetResponseDTO.class);
    }

    public HoursWorkedSheetResponseDTO updateHoursWorkedSheet(Integer id, HoursWorkedSheetRequestDTO hoursWorkedSheetRequestDTO) {
        validateHoursWorkedSheetRequestDTO(hoursWorkedSheetRequestDTO);
        HoursWorkedSheet hoursWorkedSheet = searchHoursWorkedSheetById(id);
        hoursWorkedSheetRequestMapper.updateSourceFromDestination(hoursWorkedSheet, hoursWorkedSheetRequestDTO);
        HoursWorkedSheet updatedHoursWorkedSheet = hoursWorkedSheetRepository.save(hoursWorkedSheet);
        return hoursWorkedSheetResponseMapper.toDTO(updatedHoursWorkedSheet, HoursWorkedSheetResponseDTO.class);
    }

    public void deleteHoursWorkedSheet(Integer id) {
        HoursWorkedSheet hoursWorkedSheet = searchHoursWorkedSheetById(id);
        hoursWorkedSheetRepository.delete(hoursWorkedSheet);
    }

    private void validateHoursWorkedSheetRequestDTO(HoursWorkedSheetRequestDTO hoursWorkedSheetRequestDTO) {
        if (hoursWorkedSheetRequestDTO.getEmployeeId() == null)  throw new IllegalArgumentException("Employee ID is required");
        if (hoursWorkedSheetRequestDTO.getDate() == null)  throw new IllegalArgumentException("Date is required");
    }
}