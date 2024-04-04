package com.github.faening.eng_soft_fp_api.domain.mapper.department;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
@Service
public class DepartmentResponseMapper extends AbstractMapper<Department, DepartmentResponseDTO> {
    @Autowired
    public DepartmentResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    public DepartmentResponseDTO mapDepartmentToDepartmentResponseDTO(Department department) {
        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    public List<DepartmentResponseDTO> mapDepartmentToDepartmentResponseDTO(List<Department> departments) {
        return List.of(modelMapper.map(departments, DepartmentResponseDTO[].class));
    }

    public Department mapDepartmentResponseDTOToDepartment(DepartmentResponseDTO departmentResponseDTO) {
        return modelMapper.map(departmentResponseDTO, Department.class);
    }

    public List<Department> mapDepartmentResponseDTOToDepartment(List<DepartmentResponseDTO> departmentResponseDTOs) {
        return List.of(modelMapper.map(departmentResponseDTOs, Department[].class));
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Department.class, DepartmentResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Department::getId, DepartmentResponseDTO::setId);
                mapper.when(notNull).map(Department::getDescription, DepartmentResponseDTO::setDescription);
                mapper.when(notNull).map(Department::getEnabled, DepartmentResponseDTO::setEnabled);
                mapper.when(notNull).map(Department::getManager, DepartmentResponseDTO::setManager);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), DepartmentResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), DepartmentResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(DepartmentResponseDTO.class, Department.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(DepartmentResponseDTO::getId, Department::setId);
                mapper.when(notNull).map(DepartmentResponseDTO::getDescription, Department::setDescription);
                mapper.when(notNull).map(DepartmentResponseDTO::getEnabled, Department::setEnabled);
                mapper.when(notNull).map(DepartmentResponseDTO::getManager, Department::setManager);

                mapper.when(notNull).<LocalDateTime>map(DepartmentResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(DepartmentResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}