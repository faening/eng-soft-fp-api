package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/companies")
public class CompanyController extends AbstractController<CompanyRequestDTO, CompanyResponseDTO> {
    @Autowired
    public CompanyController(CompanyService service) {
        super(service);
    }

    @Override
    public ResponseEntity<List<CompanyResponseDTO>> getAll() {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CompanyResponseDTO> update(Integer id, CompanyRequestDTO request) {
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return ResponseEntity.noContent().build();
    }
}