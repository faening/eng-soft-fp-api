package com.github.faening.eng_soft_fp_api.domain.mapper.job;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.data.model.Job;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.job.JobRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.DepartmentService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class JobRequestMapper extends AbstractMapper<Job, JobRequestDTO> {
    private final DepartmentService departmentService;

    @Autowired
    public JobRequestMapper(ModelMapper modelMapper, DepartmentService departmentService) {
        super(modelMapper);
        this.departmentService = departmentService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Job.class, JobRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Job::getDescription, JobRequestDTO::setDescription);
                mapper.when(notNull).map(Job::getExperienceLevel, JobRequestDTO::setExperienceLevel);
                mapper.when(notNull).map(Job::getBaseSalary, JobRequestDTO::setBaseSalary);
                mapper.when(notNull).map(Job::getDangerousness, JobRequestDTO::setDangerousness);
                mapper.when(notNull).map(Job::getUnhealthiness, JobRequestDTO::setUnhealthiness);
                mapper.when(notNull).map(Job::getDepartment, JobRequestDTO::setDepartmentId);
                mapper.when(notNull).map(Job::getEnabled, JobRequestDTO::setEnabled);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Department> departmentIdToDepartmentConverter = context -> departmentService.getEntityById(context.getSource());

        modelMapper.createTypeMap(JobRequestDTO.class, Job.class)
            .addMappings(mapper -> {
                mapper.skip(Job::setId);
                mapper.skip(Job::setEntityMetadata);

                mapper.when(notNull).map(JobRequestDTO::getDescription, Job::setDescription);
                mapper.when(notNull).map(JobRequestDTO::getExperienceLevel, Job::setExperienceLevel);
                mapper.when(notNull).map(JobRequestDTO::getBaseSalary, Job::setBaseSalary);
                mapper.when(notNull).map(JobRequestDTO::getDangerousness, Job::setDangerousness);
                mapper.when(notNull).map(JobRequestDTO::getUnhealthiness, Job::setUnhealthiness);
                mapper.using(departmentIdToDepartmentConverter).map(JobRequestDTO::getDepartmentId, Job::setDepartment);
                mapper.when(notNull).map(JobRequestDTO::getEnabled, Job::setEnabled);
            });
    }
}