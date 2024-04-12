package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Job;
import com.github.faening.eng_soft_fp_api.data.repository.JobRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
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
    private final JobRepository repository;
    private final JobRequestMapper requestMapper;
    private final JobResponseMapper responseMapper;
    private final TaxOrValueService taxOrValueService;

    private static final String JOB_DESCRIPTION_VALIDATION_MESSAGE = "jobService.validation.description";
    private static final String JOB_EXPERIENCE_LEVEL_VALIDATION_MESSAGE = "jobService.validation.experienceLevel";
    private static final String JOB_DEPARTMENT_ID_VALIDATION_MESSAGE = "jobService.validation.departmentId";

    @Autowired
    public JobService(
        JobRepository repository,
        JobRequestMapper requestMapper,
        JobResponseMapper responseMapper,
        TaxOrValueService taxOrValueService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.taxOrValueService = taxOrValueService;
    }

    @Override
    public List<JobResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(jobs -> responseMapper.toDTO(jobs, JobResponseDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public JobResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchJobEntityById(id), JobResponseDTO.class);
    }

    @Override
    public JobResponseDTO create(JobRequestDTO request) {
        validate(request);
        Job job = requestMapper.toEntity(request, Job.class);
        Job createdJob = repository.save(job);
        return responseMapper.toDTO(createdJob, JobResponseDTO.class);
    }

    @Override
    public JobResponseDTO update(Integer id, JobRequestDTO request) {
        validate(id);
        validate(request);
        Job job = searchJobEntityById(id);
        requestMapper.updateSourceFromDestination(job, request);
        Job updatedJob = repository.save(job);
        return responseMapper.toDTO(updatedJob, JobResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        Job job = searchJobEntityById(id);
        repository.delete(job);
    }

    private Job searchJobEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(JobRequestDTO jobRequestDTO) {
        super.validate(jobRequestDTO);
        if (jobRequestDTO.getDescription() == null || jobRequestDTO.getDescription().isEmpty()) throw new IllegalArgumentException(getLocalizedMessage(JOB_DESCRIPTION_VALIDATION_MESSAGE));
        if (jobRequestDTO.getExperienceLevel() == null) throw new IllegalArgumentException(getLocalizedMessage(JOB_EXPERIENCE_LEVEL_VALIDATION_MESSAGE));
        if (jobRequestDTO.getDepartmentId() == null) throw new IllegalArgumentException(getLocalizedMessage(JOB_DEPARTMENT_ID_VALIDATION_MESSAGE));
        if (jobRequestDTO.getDangerousness() == null) jobRequestDTO.setDangerousness(false);
        if (jobRequestDTO.getUnhealthiness() == null) jobRequestDTO.setUnhealthiness(false);
        if (jobRequestDTO.getEnabled() == null) jobRequestDTO.setEnabled(true);
        if (jobRequestDTO.getBaseSalary() == null) jobRequestDTO.setBaseSalary(
            taxOrValueService.getByType(TaxOrValueType.MINIMUM_WAGE).get(0).getFixedValue()
        );
    }
}