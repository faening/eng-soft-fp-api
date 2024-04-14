package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.trip_expense.TripExpenseRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.trip_expense.TripExpenseResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.TripExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/trip-expenses")
public class TripExpenseController extends AbstractController<TripExpenseRequestDTO, TripExpenseResponseDTO> {
    private final TripExpenseService service;

    @Autowired
    public TripExpenseController(TripExpenseService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = { "/employee" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TripExpenseResponseDTO> getTripExpensesByEmployeeIdAndDate(
        @RequestParam Integer employeeId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        TripExpenseResponseDTO tripExpense = service.getByEmployeeIdAndDate(employeeId, date);
        return ResponseEntity.ok(tripExpense);
    }
}