package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/loans")
public class LoanController extends AbstractController<LoanRequestDTO, LoanResponseDTO> {
    private final LoanService service;

    @Autowired
    public LoanController(LoanService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(
        value = {"/employee"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LoanResponseDTO>> getLoanByEmployeeIdAndSpecs(
        @RequestParam Integer employeeId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate approvalDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate companyPaymentDate,
        @RequestParam(required = false) PaymentStatus paymentStatus
    ) {
        List<LoanResponseDTO> hoursWorkedSheets = service.searchLoanByEmployeeIdAndSpecs(employeeId, requestDate, approvalDate, companyPaymentDate, paymentStatus);
        return ResponseEntity.ok(hoursWorkedSheets);
    }

    @Override
    public ResponseEntity<LoanResponseDTO> update(Integer id, LoanRequestDTO request) {
        return ResponseEntity.noContent().build();
    }
}