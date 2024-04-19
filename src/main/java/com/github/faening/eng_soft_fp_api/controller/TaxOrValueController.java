package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.TaxOrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/tax-or-values")
public class TaxOrValueController extends AbstractController<TaxOrValueRequestDTO, TaxOrValueResponseDTO> {
    private final TaxOrValueService service;

    @Autowired
    public TaxOrValueController(TaxOrValueService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = {"/type/{type}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaxOrValueResponseDTO>> getByType(
        @PathVariable(value = "type") TaxOrValueType type
    ) {
        List<TaxOrValueResponseDTO> hoursWorkedSheet = service.getByType(type);
        return ResponseEntity.ok(hoursWorkedSheet);
    }
}