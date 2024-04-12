package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/loans")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LoanResponseDTO>> getAllLoans() {
        List<LoanResponseDTO> hoursWorkedSheets = loanService.getAll();
        return ResponseEntity.ok(hoursWorkedSheets);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoanResponseDTO> getLoanById(
        @PathVariable(value = "id") Integer id
    ) {
        LoanResponseDTO hoursWorkedSheets = loanService.getById(id);
        return ResponseEntity.ok(hoursWorkedSheets);
    }

    @GetMapping(
        value = { "/employee" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LoanResponseDTO>> getLoanByEmployeeIdAndSpecs(
        @RequestParam Integer employeeId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate approvalDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate companyPaymentDate,
        @RequestParam(required = false) PaymentStatus paymentStatus
    ) {
        List<LoanResponseDTO> hoursWorkedSheets = loanService.searchLoanByEmployeeIdAndSpecs(employeeId, requestDate, approvalDate, companyPaymentDate, paymentStatus);
        return ResponseEntity.ok(hoursWorkedSheets);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoanResponseDTO> createLoan(
        @RequestBody LoanRequestDTO request
    ) {
        LoanResponseDTO createdLoan = loanService.create(request);
        return ResponseEntity.ok(createdLoan);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteLoan(
        @PathVariable(value = "id") Integer id
    ) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
}