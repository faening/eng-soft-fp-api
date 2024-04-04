package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftDTO;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/work-shifts")
public class WorkShiftController {

    private final WorkShiftService workShiftService;

    @Autowired
    public WorkShiftController(WorkShiftService workShiftService) {
        this.workShiftService = workShiftService;
    }

    @GetMapping(
        value = {""},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkShiftDTO>> getAllWorkShifts() {
        List<WorkShiftDTO> workShifts = workShiftService.getAllWorkShifts();
        return ResponseEntity.ok(workShifts);
    }

    @GetMapping(
        value = {"/{id}"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkShiftDTO> getWorkShiftById(
        @PathVariable(value = "id") Integer id
    ) {
        WorkShiftDTO workShift = workShiftService.getWorkShiftById(id);
        return ResponseEntity.ok(workShift);
    }

    @PostMapping(
        value = {""},
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkShiftDTO> createWorkShift(
        @RequestBody WorkShiftDTO workShiftDTO
    ) {
        WorkShiftDTO createdWorkShift = workShiftService.createWorkShift(workShiftDTO);
        return ResponseEntity.ok(createdWorkShift);
    }

    @PatchMapping(
        value = {"/{id}"},
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkShiftDTO> updateWorkShift(
        @PathVariable(value = "id") Integer id,
        @RequestBody WorkShiftDTO workShiftDTO
    ) {
        WorkShiftDTO updatedWorkShift = workShiftService.updateWorkShift(id, workShiftDTO);
        return ResponseEntity.ok(updatedWorkShift);
    }

    @DeleteMapping(
        value = {"/{id}"}
    )
    public ResponseEntity<Void> deleteWorkShift(
        @PathVariable(value = "id") Integer id
    ) {
        workShiftService.deleteWorkShift(id);
        return ResponseEntity.noContent().build();
    }
}