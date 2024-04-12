package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.WorkShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
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
    public ResponseEntity<List<WorkShiftResponseDTO>> getAllWorkShifts() {
        List<WorkShiftResponseDTO> workShifts = workShiftService.getAll();
        return ResponseEntity.ok(workShifts);
    }

    @GetMapping(
        value = {"/{id}"},
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkShiftResponseDTO> getWorkShiftById(
        @PathVariable(value = "id") Integer id
    ) {
        WorkShiftResponseDTO workShift = workShiftService.getById(id);
        return ResponseEntity.ok(workShift);
    }

    @PostMapping(
        value = {""},
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkShiftResponseDTO> createWorkShift(
        @RequestBody WorkShiftRequestDTO workShiftRequestDTO
    ) {
        WorkShiftResponseDTO createdWorkShift = workShiftService.create(workShiftRequestDTO);
        return ResponseEntity.ok(createdWorkShift);
    }

    @PatchMapping(
        value = {"/{id}"},
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkShiftResponseDTO> updateWorkShift(
        @PathVariable(value = "id") Integer id,
        @RequestBody WorkShiftRequestDTO workShiftRequestDTO
    ) {
        WorkShiftResponseDTO updatedWorkShift = workShiftService.update(id, workShiftRequestDTO);
        return ResponseEntity.ok(updatedWorkShift);
    }

    @DeleteMapping(
        value = {"/{id}"}
    )
    public ResponseEntity<Void> deleteWorkShift(
        @PathVariable(value = "id") Integer id
    ) {
        workShiftService.delete(id);
        return ResponseEntity.noContent().build();
    }
}