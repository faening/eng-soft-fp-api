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

@SuppressWarnings("unused")
@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobRequestMapper jobRequestMapper;
    private final JobResponseMapper jobResponseMapper;

    @Autowired
    public JobService(
        JobRepository jobRepository,
        JobRequestMapper jobRequestMapper,
        JobResponseMapper jobResponseMapper) {
        this.jobRepository = jobRepository;
        this.jobRequestMapper = jobRequestMapper;
        this.jobResponseMapper = jobResponseMapper;
    }

    private Job searchJobById(Integer id) {
        return jobRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum trabalho encontrado com o id: " + id)
        );
    }

    public List<JobResponseDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobResponseMapper.mapJobToJobResponseDTO(jobs);
    }

    public JobResponseDTO getJobById(Integer id) {
        Job job = searchJobById(id);
        return jobResponseMapper.mapJobToJobResponseDTO(job);
    }

    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {
        validateJobRequestDTO(jobRequestDTO);
        Job job = jobRequestMapper.mapJobRequestDTOToJob(jobRequestDTO);
        Job createdJob = jobRepository.save(job);
        return jobResponseMapper.mapJobToJobResponseDTO(createdJob);
    }

    public JobResponseDTO updateJob(Integer id, JobRequestDTO jobRequestDTO) {
        validateJobRequestDTO(jobRequestDTO);
        Job job = searchJobById(id);
        jobRequestMapper.updateSourceFromDestination(job, jobRequestDTO);
        Job updatedJob = jobRepository.save(job);
        return jobResponseMapper.mapJobToJobResponseDTO(updatedJob);
    }

    public void deleteJob(Integer id) {
        Job job = searchJobById(id);
        jobRepository.delete(job);
    }

    private void validateJobRequestDTO(JobRequestDTO jobRequestDTO) {
        if (jobRequestDTO.getDescription() == null || jobRequestDTO.getDescription().isEmpty()) throw new IllegalArgumentException("Description is required");
        if (jobRequestDTO.getExperienceLevel() == null) throw new IllegalArgumentException("Experience Level is required");
        if (jobRequestDTO.getDepartmentId() == null) throw new IllegalArgumentException("Department ID is required");
        if (jobRequestDTO.getDangerousness() == null) jobRequestDTO.setDangerousness(false);
        if (jobRequestDTO.getUnhealthiness() == null) jobRequestDTO.setUnhealthiness(false);
        if (jobRequestDTO.getEnabled() == null) jobRequestDTO.setEnabled(true);
        // TODO: Se o `baseSalary` não for informado, definir o valor padrão com o salário mínimo vigente
    }
}