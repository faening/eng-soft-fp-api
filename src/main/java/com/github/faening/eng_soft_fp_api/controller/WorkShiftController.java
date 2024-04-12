package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1/work-shifts")
public class WorkShiftController extends AbstractController<WorkShiftRequestDTO, WorkShiftResponseDTO> {
    @Autowired
    public WorkShiftController(WorkShiftService service) {
        super(service);
    }
}