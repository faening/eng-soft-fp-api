package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/tax-or-values")
public class TaxOrValueController {
    private final TaxOrValueService taxOrValueService;

    @Autowired
    public TaxOrValueController(TaxOrValueService taxOrValueService) {
        this.taxOrValueService = taxOrValueService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TaxOrValueResponseDTO>> getAllTaxOrValues() {
        List<TaxOrValueResponseDTO> taxOrValues = taxOrValueService.getAll();
        return ResponseEntity.ok(taxOrValues);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TaxOrValueResponseDTO> getTaxOrValueById(
        @PathVariable(value = "id") Integer id
    ) {
        TaxOrValueResponseDTO hoursWorkedSheet = taxOrValueService.getById(id);
        return ResponseEntity.ok(hoursWorkedSheet);
    }

    @GetMapping(
        value = { "/type/{type}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TaxOrValueResponseDTO>> getTaxOrValueByType(
        @PathVariable(value = "type") TaxOrValueType type
    ) {
        List<TaxOrValueResponseDTO> hoursWorkedSheet = taxOrValueService.getByType(type);
        return ResponseEntity.ok(hoursWorkedSheet);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TaxOrValueResponseDTO> createTaxOrValue(
        @RequestBody TaxOrValueRequestDTO taxOrValueRequestDTO
    ) {
        TaxOrValueResponseDTO createdSale = taxOrValueService.create(taxOrValueRequestDTO);
        return ResponseEntity.ok(createdSale);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TaxOrValueResponseDTO> updateTaxOrValue(
        @PathVariable(value = "id") Integer id,
        @RequestBody TaxOrValueRequestDTO taxOrValueRequestDTO
    ) {
        TaxOrValueResponseDTO updatedSale = taxOrValueService.update(id, taxOrValueRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteTaxOrValue(
        @PathVariable(value = "id") Integer id
    ) {
        taxOrValueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}