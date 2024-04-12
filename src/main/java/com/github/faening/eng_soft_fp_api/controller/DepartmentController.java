package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/departments")
public class DepartmentController extends AbstractController<DepartmentRequestDTO, DepartmentResponseDTO>{
    @Autowired
    public DepartmentController(DepartmentService service) {
        super(service);
    }
}