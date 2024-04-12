package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CompanyResponseDTO> getCompanyById(
        @PathVariable(value = "id") Integer id
    ) {
        CompanyResponseDTO companyResponseDTO = companyService.getById(id);
        return ResponseEntity.ok(companyResponseDTO);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CompanyResponseDTO> updateCompany(
        @PathVariable(value = "id") Integer id,
        @RequestBody CompanyRequestDTO companyRequestDTO
    ) {
        CompanyResponseDTO updatedCompany = companyService.update(id, companyRequestDTO);
        return ResponseEntity.ok(updatedCompany);
    }
}