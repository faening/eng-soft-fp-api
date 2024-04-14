package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.legal_charge.LegalChargeRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge.LegalChargeResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.LegalChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/legal-charges")
public class LegalChargeController extends AbstractController<LegalChargeRequestDTO, LegalChargeResponseDTO> {
    private final LegalChargeService service;

    @Autowired
    public LegalChargeController(LegalChargeService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(
        value = {"/employee"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LegalChargeResponseDTO>> getLegalChargeByEmployeeIdAndSpecs(
        @RequestParam Integer employeeId,
        @RequestParam(required = false) BigDecimal legalChargeAmount,
        @RequestParam(required = false) BigDecimal percentage,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
        @RequestParam(required = false) Boolean isRecurring
    ) {
        List<LegalChargeResponseDTO> legalCharge = service.searchLoanByEmployeeIdAndSpecs(employeeId, legalChargeAmount, percentage, releaseDate, isRecurring);
        return ResponseEntity.ok(legalCharge);
    }

    @Override
    public ResponseEntity<LegalChargeResponseDTO> update(Integer id, LegalChargeRequestDTO request) {
        return ResponseEntity.noContent().build();
    }
}