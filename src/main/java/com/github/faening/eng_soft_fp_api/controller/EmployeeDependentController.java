package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/employee-dependents")
public class EmployeeDependentController {
    private final EmployeeDependentService employeeDependentService;

    @Autowired
    public EmployeeDependentController(EmployeeDependentService employeeDependentService) {
        this.employeeDependentService = employeeDependentService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<EmployeeDependentResponseDTO>> getAllEmployeeDependents() {
        List<EmployeeDependentResponseDTO> dependents = employeeDependentService.getAll();
        return ResponseEntity.ok(dependents);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeDependentResponseDTO> getEmployeeDependentById(
        @PathVariable(value = "id") Integer id
    ) {
        EmployeeDependentResponseDTO dependent = employeeDependentService.getById(id);
        return ResponseEntity.ok(dependent);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeDependentResponseDTO> createEmployeeDependent(
        @RequestBody EmployeeDependentRequestDTO employeeDependentRequestDTO
    ) {
        EmployeeDependentResponseDTO createdJob = employeeDependentService.create(employeeDependentRequestDTO);
        return ResponseEntity.ok(createdJob);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeDependentResponseDTO> updateEmployeeDependent(
        @PathVariable(value = "id") Integer id,
        @RequestBody EmployeeDependentRequestDTO employeeDependentRequestDTO
    ) {
        EmployeeDependentResponseDTO updatedJob = employeeDependentService.update(id, employeeDependentRequestDTO);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteEmployeeDependent(
        @PathVariable(value = "id") Integer id
    ) {
        employeeDependentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}