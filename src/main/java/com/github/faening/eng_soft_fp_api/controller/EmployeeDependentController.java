package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeDependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/employee-dependents")
public class EmployeeDependentController extends AbstractController<EmployeeDependentRequestDTO, EmployeeDependentResponseDTO> {
    @Autowired
    public EmployeeDependentController(EmployeeDependentService service) {
        super(service);
    }
}