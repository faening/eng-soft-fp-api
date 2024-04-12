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
public class HoursWorkedSheetController extends AbstractController<HoursWorkedSheetRequestDTO, HoursWorkedSheetResponseDTO> {
    private final HoursWorkedSheetService service;

    @Autowired
    public HoursWorkedSheetController(HoursWorkedSheetService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = { "/employee" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HoursWorkedSheetResponseDTO>> getHoursWorkedSheetsByEmployeeIdAndDateRange(
        @RequestParam Integer employeeId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<HoursWorkedSheetResponseDTO> sales = service.getWorkedHoursByEmployeeIdAndDateRange(employeeId, startDate, endDate);
        return ResponseEntity.ok(sales);
    }
}