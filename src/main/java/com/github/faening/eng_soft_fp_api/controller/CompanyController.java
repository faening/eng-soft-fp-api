package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.CompanyVO;
import com.github.faening.eng_soft_fp_api.domain.service.CompanyService;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(
        value = {"", "/", "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CompanyVO> getCompanyById(
        @PathVariable(value = "id", required = false) Integer id
    ) {
        if (id == null) { id = 1; }
        CompanyVO companyVO = companyService.getCompanyById(id);
        if (companyVO == null) { throw new ResourceNotFoundException("Nenhuma empresa encontrada com o id " + id); }
        return ResponseEntity.ok(companyVO);
    }
}