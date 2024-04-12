package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.AbsenceType;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.AbsenceSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/absence-sheets")
public class AbsenceSheetController {
    private final AbsenceSheetService absenceSheetService;

    @Autowired
    public AbsenceSheetController(AbsenceSheetService absenceSheetService) {
        this.absenceSheetService = absenceSheetService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AbsenceSheetResponseDTO>> getAllAbsenceSheets() {
        List<AbsenceSheetResponseDTO> absenceSheets = absenceSheetService.getAll();
        return ResponseEntity.ok(absenceSheets);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AbsenceSheetResponseDTO> getAbsenceSheetById(
        @PathVariable(value = "id") Integer id
    ) {
        AbsenceSheetResponseDTO absenceSheet = absenceSheetService.getById(id);
        return ResponseEntity.ok(absenceSheet);
    }

    @GetMapping(
        value = { "/employee" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AbsenceSheetResponseDTO>> getAbsenceSheetsByEmployeeIdAndTypeAndDateRange(
        @RequestParam Integer employeeId,
        @RequestParam AbsenceType type,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<AbsenceSheetResponseDTO> absenceSheets = absenceSheetService.getAbsenceSheetsByEmployeeIdAndTypeAndDateRange(employeeId, type, startDate, endDate);
        return ResponseEntity.ok(absenceSheets);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AbsenceSheetResponseDTO> createAbsenceSheet(
        @RequestBody AbsenceSheetRequestDTO absenceSheetRequestDTO
        ) {
        AbsenceSheetResponseDTO createdSale = absenceSheetService.create(absenceSheetRequestDTO);
        return ResponseEntity.ok(createdSale);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AbsenceSheetResponseDTO> updateAbsenceSheet(
        @PathVariable(value = "id") Integer id,
        @RequestBody AbsenceSheetRequestDTO absenceSheetRequestDTO
    ) {
        AbsenceSheetResponseDTO updatedSale = absenceSheetService.update(id, absenceSheetRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteAbsenceSheet(
        @PathVariable(value = "id") Integer id
    ) {
        absenceSheetService.delete(id);
        return ResponseEntity.noContent().build();
    }
}