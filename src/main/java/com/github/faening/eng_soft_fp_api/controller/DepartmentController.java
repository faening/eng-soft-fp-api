package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        List<DepartmentResponseDTO> departmentResponseDTOList = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentResponseDTOList);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(
        @PathVariable(value = "id") Integer id
    ) {
        DepartmentResponseDTO departmentResponseDTO = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentResponseDTO);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
        @RequestBody DepartmentRequestDTO departmentRequestDTO
    ) {
        DepartmentResponseDTO createdDepartment = departmentService.createDepartment(departmentRequestDTO);
        return ResponseEntity.ok(createdDepartment);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
        @PathVariable(value = "id") Integer id,
        @RequestBody DepartmentRequestDTO departmentRequestDTO
    ) {
        DepartmentResponseDTO updatedDepartment = departmentService.updateDepartment(id, departmentRequestDTO);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteDepartment(
        @PathVariable(value = "id") Integer id
    ) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}