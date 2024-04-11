package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.HoursWorkedSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/hours-worked-sheets")
public class HoursWorkedSheetController {
    private final HoursWorkedSheetService hoursWorkedSheetService;

    @Autowired
    public HoursWorkedSheetController(HoursWorkedSheetService hoursWorkedSheetService) {
        this.hoursWorkedSheetService = hoursWorkedSheetService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<HoursWorkedSheetResponseDTO>> getAllHoursWorkedSheets() {
        List<HoursWorkedSheetResponseDTO> hoursWorkedSheets = hoursWorkedSheetService.getAll();
        return ResponseEntity.ok(hoursWorkedSheets);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HoursWorkedSheetResponseDTO> getHoursWorkedSheetById(
        @PathVariable(value = "id") Integer id
    ) {
        HoursWorkedSheetResponseDTO hoursWorkedSheet = hoursWorkedSheetService.getById(id);
        return ResponseEntity.ok(hoursWorkedSheet);
    }

    @GetMapping(
        value = { "/employee" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<HoursWorkedSheetResponseDTO>> getHoursWorkedSheetsByEmployeeIdAndDateRange(
        @RequestParam Integer employeeId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<HoursWorkedSheetResponseDTO> sales = hoursWorkedSheetService.getWorkedHoursByEmployeeIdAndDateRange(employeeId, startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HoursWorkedSheetResponseDTO> createHoursWorkedSheet(
        @RequestBody HoursWorkedSheetRequestDTO hoursWorkedSheetRequestDTO
    ) {
        HoursWorkedSheetResponseDTO createdSale = hoursWorkedSheetService.create(hoursWorkedSheetRequestDTO);
        return ResponseEntity.ok(createdSale);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HoursWorkedSheetResponseDTO> updateHoursWorkedSheet(
        @PathVariable(value = "id") Integer id,
        @RequestBody HoursWorkedSheetRequestDTO hoursWorkedSheetRequestDTO
    ) {
        HoursWorkedSheetResponseDTO updatedSale = hoursWorkedSheetService.update(id, hoursWorkedSheetRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteHoursWorkedSheet(
        @PathVariable(value = "id") Integer id
    ) {
        hoursWorkedSheetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}