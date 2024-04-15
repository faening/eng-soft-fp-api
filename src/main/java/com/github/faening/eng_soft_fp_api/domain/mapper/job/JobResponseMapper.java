package com.github.faening.eng_soft_fp_api.domain.mapper.job;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.data.model.Job;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.DepartmentService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class JobResponseMapper extends AbstractMapper<Job, JobResponseDTO> {
    private final DepartmentService departmentService;

    @Autowired
    public JobResponseMapper(ModelMapper modelMapper, DepartmentService departmentService) {
        super(modelMapper);
        this.departmentService = departmentService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Job.class, JobResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Job::getId, JobResponseDTO::setId);
                mapper.when(notNull).map(Job::getDescription, JobResponseDTO::setDescription);
                mapper.when(notNull).map(Job::getExperienceLevel, JobResponseDTO::setExperienceLevel);
                mapper.when(notNull).map(Job::getBaseSalary, JobResponseDTO::setBaseSalary);
                mapper.when(notNull).map(Job::getDangerousness, JobResponseDTO::setDangerousness);
                mapper.when(notNull).map(Job::getUnhealthiness, JobResponseDTO::setUnhealthiness);
                mapper.when(notNull).map(src -> src.getDepartment().getId(), JobResponseDTO::setDepartmentId);
                mapper.when(notNull).map(Job::getEnabled, JobResponseDTO::setEnabled);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), JobResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), JobResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Department> departmentIdToDepartmentConverter = context -> departmentService.getEntityById(context.getSource());

        modelMapper.createTypeMap(JobResponseDTO.class, Job.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(JobResponseDTO::getId, Job::setId);
                mapper.when(notNull).map(JobResponseDTO::getDescription, Job::setDescription);
                mapper.when(notNull).map(JobResponseDTO::getExperienceLevel, Job::setExperienceLevel);
                mapper.when(notNull).map(JobResponseDTO::getBaseSalary, Job::setBaseSalary);
                mapper.when(notNull).map(JobResponseDTO::getDangerousness, Job::setDangerousness);
                mapper.when(notNull).map(JobResponseDTO::getUnhealthiness, Job::setUnhealthiness);
                mapper.when(notNull).using(departmentIdToDepartmentConverter).map(JobResponseDTO::getDepartmentId, Job::setDepartment);
                mapper.when(notNull).map(JobResponseDTO::getEnabled, Job::setEnabled);
                mapper.when(notNull).<LocalDateTime>map(JobResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(JobResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}