package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.model.job.JobRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(
        value = { "" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
        List<JobResponseDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping(
        value = { "/{id}" },
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponseDTO> getJobById(
        @PathVariable(value = "id") Integer id
    ) {
        JobResponseDTO job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    @PostMapping(
        value = { "" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponseDTO> createJob(
        @RequestBody JobRequestDTO jobRequestDTO
    ) {
        JobResponseDTO createdJob = jobService.createJob(jobRequestDTO);
        return ResponseEntity.ok(createdJob);
    }

    @PatchMapping(
        value = { "/{id}" },
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponseDTO> updateJob(
        @PathVariable(value = "id") Integer id,
        @RequestBody JobRequestDTO jobRequestDTO
    ) {
        JobResponseDTO updatedJob = jobService.updateJob(id, jobRequestDTO);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping(
        value = { "/{id}" }
    )
    public ResponseEntity<Void> deleteJob(
        @PathVariable(value = "id") Integer id
    ) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}