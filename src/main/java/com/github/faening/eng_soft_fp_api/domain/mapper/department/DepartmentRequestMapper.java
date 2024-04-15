package com.github.faening.eng_soft_fp_api.domain.mapper.department;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class DepartmentRequestMapper extends AbstractMapper<Department, DepartmentRequestDTO> {
    @Autowired
    public DepartmentRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Department.class, DepartmentRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Department::getDescription, DepartmentRequestDTO::setDescription);
                mapper.when(notNull).map(Department::getEnabled, DepartmentRequestDTO::setEnabled);
                mapper.when(notNull).map(Department::getManager, DepartmentRequestDTO::setManagerId);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(DepartmentRequestDTO.class, Department.class)
            .addMappings(mapper -> {
                mapper.skip(Department::setId);
                mapper.skip(Department::setEntityMetadata);
                mapper.when(notNull).map(DepartmentRequestDTO::getDescription, Department::setDescription);
                mapper.when(notNull).map(DepartmentRequestDTO::getEnabled, Department::setEnabled);
                mapper.when(notNull).map(DepartmentRequestDTO::getManagerId, Department::setManager);
            });
    }
}