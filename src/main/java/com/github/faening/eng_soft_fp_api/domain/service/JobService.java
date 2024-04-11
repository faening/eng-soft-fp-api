package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Job;
import com.github.faening.eng_soft_fp_api.data.repository.JobRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.job.JobRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.job.JobResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class JobService extends AbstractService<JobRequestDTO, JobResponseDTO> {
    private final JobRepository jobRepository;
    private final JobRequestMapper jobRequestMapper;
    private final JobResponseMapper jobResponseMapper;

    @Autowired
    public JobService(
        JobRepository jobRepository,
        JobRequestMapper jobRequestMapper,
        JobResponseMapper jobResponseMapper
    ) {
        this.jobRepository = jobRepository;
        this.jobRequestMapper = jobRequestMapper;
        this.jobResponseMapper = jobResponseMapper;
    }

    @Override
    public List<JobResponseDTO> getAll() {
        return jobRepository
            .findAll()
            .stream()
            .map(jobs -> jobResponseMapper.toDTO(jobs, JobResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public JobResponseDTO getById(Integer id) {
        validateId(id);
        return jobResponseMapper.toDTO(searchJobById(id), JobResponseDTO.class);
    }

    @Override
    public JobResponseDTO create(JobRequestDTO request) {
        validateJobRequestDTO(request);
        Job job = jobRequestMapper.toEntity(request, Job.class);
        Job createdJob = jobRepository.save(job);
        return jobResponseMapper.toDTO(createdJob, JobResponseDTO.class);
    }

    @Override
    public JobResponseDTO update(Integer id, JobRequestDTO request) {
        validateId(id);
        validateJobRequestDTO(request);
        Job job = searchJobById(id);
        jobRequestMapper.updateSourceFromDestination(job, request);
        Job updatedJob = jobRepository.save(job);
        return jobResponseMapper.toDTO(updatedJob, JobResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        Job job = searchJobById(id);
        jobRepository.delete(job);
    }

    public Job searchJobById(Integer id) {
        return jobRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum trabalho encontrado com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage("jobService.validation.jobId"));
    }

    private void validateJobRequestDTO(JobRequestDTO jobRequestDTO) {
        if (jobRequestDTO.getDescription() == null || jobRequestDTO.getDescription().isEmpty())
            throw new IllegalArgumentException(getLocalizedMessage("jobService.validation.description"));
        if (jobRequestDTO.getExperienceLevel() == null) throw new IllegalArgumentException(getLocalizedMessage("jobService.validation.experienceLevel"));
        if (jobRequestDTO.getDepartmentId() == null) throw new IllegalArgumentException(getLocalizedMessage("jobService.validation.departmentId"));
        if (jobRequestDTO.getDangerousness() == null) jobRequestDTO.setDangerousness(false);
        if (jobRequestDTO.getUnhealthiness() == null) jobRequestDTO.setUnhealthiness(false);
        if (jobRequestDTO.getEnabled() == null) jobRequestDTO.setEnabled(true);
        // TODO: Se o `baseSalary` não for informado, definir o valor padrão com o salário mínimo vigente
    }
}