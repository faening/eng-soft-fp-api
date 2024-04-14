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
public class AbsenceSheetController extends AbstractController<AbsenceSheetRequestDTO, AbsenceSheetResponseDTO> {
    private final AbsenceSheetService service;

    @Autowired
    public AbsenceSheetController(AbsenceSheetService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = { "/employee" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AbsenceSheetResponseDTO>> getByEmployeeIdAndSpecs(
        @RequestParam Integer employeeId,
        @RequestParam AbsenceType type,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<AbsenceSheetResponseDTO> absenceSheets = service.getAbsenceSheetByEmployeeIdAndSpecs(employeeId, type, startDate, endDate);
        return ResponseEntity.ok(absenceSheets);
    }
}