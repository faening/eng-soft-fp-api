package com.github.faening.eng_soft_fp_api.domain.mapper.job;

import com.github.faening.eng_soft_fp_api.data.model.Job;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class JobResponseMapper extends AbstractMapper<Job, JobResponseDTO> {
    @Autowired
    public JobResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    public JobResponseDTO mapJobToJobResponseDTO(Job job) {
        return modelMapper.map(job, JobResponseDTO.class);
    }

    public List<JobResponseDTO> mapJobToJobResponseDTO(List<Job> jobs) {
        return List.of(modelMapper.map(jobs, JobResponseDTO[].class));
    }

    public Job mapJobResponseDTOToJob(JobResponseDTO jobResponseDTO) {
        return modelMapper.map(jobResponseDTO, Job.class);
    }

    public List<Job> mapJobResponseDTOToJob(List<JobResponseDTO> jobResponseDTOs) {
        return List.of(modelMapper.map(jobResponseDTOs, Job[].class));
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Job.class, JobResponseDTO.class)
            .addMappings(mapper -> {
                mapper.map(Job::getId, JobResponseDTO::setId);
                mapper.map(Job::getDescription, JobResponseDTO::setDescription);
                mapper.map(Job::getExperienceLevel, JobResponseDTO::setExperienceLevel);
                mapper.map(Job::getBaseSalary, JobResponseDTO::setBaseSalary);
                mapper.map(Job::getDangerousness, JobResponseDTO::setDangerousness);
                mapper.map(Job::getUnhealthiness, JobResponseDTO::setUnhealthiness);
                mapper.map(Job::getDepartment, JobResponseDTO::setDepartment);
                mapper.map(Job::getEnabled, JobResponseDTO::setEnabled);
                mapper.map(src -> src.getEntityMetadata().getCreatedAt(), JobResponseDTO::setCreatedAt);
                mapper.map(src -> src.getEntityMetadata().getUpdatedAt(), JobResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(JobResponseDTO.class, Job.class)
            .addMappings(mapper -> {
                mapper.map(JobResponseDTO::getId, Job::setId);
                mapper.map(JobResponseDTO::getDescription, Job::setDescription);
                mapper.map(JobResponseDTO::getExperienceLevel, Job::setExperienceLevel);
                mapper.map(JobResponseDTO::getBaseSalary, Job::setBaseSalary);
                mapper.map(JobResponseDTO::getDangerousness, Job::setDangerousness);
                mapper.map(JobResponseDTO::getUnhealthiness, Job::setUnhealthiness);
                mapper.map(JobResponseDTO::getDepartment, Job::setDepartment);
                mapper.map(JobResponseDTO::getEnabled, Job::setEnabled);

                mapper.skip(Job::setEntityMetadata);
            });
    }
}