package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher.BenefitVoucherRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher.BenefitVoucherResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.BenefitVoucherService;
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
@RequestMapping("/v1/benefit-vouchers")
public class BenefitVoucherController extends AbstractController<BenefitVoucherRequestDTO, BenefitVoucherResponseDTO> {
    private final BenefitVoucherService service;

    @Autowired
    public BenefitVoucherController(BenefitVoucherService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = {"/employee"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BenefitVoucherResponseDTO>> getBenefitVouchersByEmployeeAndSpecs(
        @RequestParam Integer employeeId,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
        @RequestParam(required = false) BenefitVoucherType benefitType,
        @RequestParam(required = false) PaymentStatus paymentStatus,
        @RequestParam(required = false) Boolean payrollDeductible
    ) {
        List<BenefitVoucherResponseDTO> benefitVoucher = service.getBenefitVouchersByEmployeeAndSpecs(employeeId, releaseDate, benefitType, paymentStatus, payrollDeductible);
        return ResponseEntity.ok(benefitVoucher);
    }
}