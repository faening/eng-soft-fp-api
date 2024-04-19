package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
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
@RequestMapping("/v1/employees")
public class EmployeeController extends AbstractController<EmployeeRequestDTO, EmployeeResponseDTO> {
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        super(service);
        this.service = service;
    }

    @GetMapping(value = { "/summary" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeSummaryDTO>> getAllEmployeeSummaries() {
        List<EmployeeSummaryDTO> departmentResponseDTOs = service.getAllSummaries();
        return ResponseEntity.ok(departmentResponseDTOs);
    }

    @GetMapping(value = { "/summary/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeSummaryDTO> getEmployeeSummaryById(
        @PathVariable(value = "id") Integer id
    ) {
        EmployeeSummaryDTO departmentResponseDTO = service.getSummaryById(id);
        return ResponseEntity.ok(departmentResponseDTO);
    }
}