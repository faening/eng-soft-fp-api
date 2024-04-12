package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.SaleService;
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
@RequestMapping("/v1/sales")
public class SaleController extends AbstractController<SaleRequestDTO, SaleResponseDTO> {
    private final SaleService service;

    @Autowired
    public SaleController(SaleService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = {"/employee"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleResponseDTO>> getSalesByEmployeeIdAndDateRange(
        @RequestParam Integer employeeId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<SaleResponseDTO> sales = service.getSalesByEmployeeIdAndDateRange(employeeId, startDate, endDate);
        return ResponseEntity.ok(sales);
    }
}