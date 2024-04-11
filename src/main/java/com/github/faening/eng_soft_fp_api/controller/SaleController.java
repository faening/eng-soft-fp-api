package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/sales")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> sales = saleService.getAll();
        return ResponseEntity.ok(sales);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SaleResponseDTO> getSaleById(
        @PathVariable(value = "id") Integer id
    ) {
        SaleResponseDTO sale = saleService.getById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping(
        value = { "/employee" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<SaleResponseDTO>> getSalesByEmployeeIdAndDateRange(
        @RequestParam Integer employeeId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<SaleResponseDTO> sales = saleService.getSalesByEmployeeIdAndDateRange(employeeId, startDate, endDate);
        return ResponseEntity.ok(sales);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SaleResponseDTO> createSale(
        @RequestBody SaleRequestDTO saleRequestDTO
    ) {
        SaleResponseDTO createdSale = saleService.create(saleRequestDTO);
        return ResponseEntity.ok(createdSale);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SaleResponseDTO> updateSale(
        @PathVariable(value = "id") Integer id,
        @RequestBody SaleRequestDTO saleRequestDTO
    ) {
        SaleResponseDTO updatedSale = saleService.update(id, saleRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteSale(
        @PathVariable(value = "id") Integer id
    ) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}