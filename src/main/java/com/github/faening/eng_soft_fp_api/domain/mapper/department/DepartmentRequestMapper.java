package com.github.faening.eng_soft_fp_api.domain.mapper.department;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class DepartmentRequestMapper extends AbstractMapper<Department, DepartmentRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    public DepartmentRequestDTO mapDepartmentToDepartmentRequestDTO(Department department) {
        return modelMapper.map(department, DepartmentRequestDTO.class);
    }

    public List<DepartmentRequestDTO> mapDepartmentToDepartmentRequestDTO(List<Department> departments) {
        return List.of(modelMapper.map(departments, DepartmentRequestDTO[].class));
    }

    public Department mapDepartmentRequestDTOToDepartment(DepartmentRequestDTO DepartmentRequestDTO) {
        return modelMapper.map(DepartmentRequestDTO, Department.class);
    }

    public List<Department> mapDepartmentRequestDTOToDepartment(List<DepartmentRequestDTO> DepartmentRequestDTOs) {
        return List.of(modelMapper.map(DepartmentRequestDTOs, Department[].class));
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
        Converter<Integer, Employee> managerIdToEmployeeConverter = context -> employeeService.searchEmployeeById(context.getSource());

        modelMapper.createTypeMap(DepartmentRequestDTO.class, Department.class)
            .addMappings(mapper -> {
                mapper.skip(Department::setId);
                mapper.skip(Department::setEntityMetadata);

                mapper.when(notNull).map(DepartmentRequestDTO::getDescription, Department::setDescription);
                mapper.map(DepartmentRequestDTO::getEnabled, Department::setEnabled);
                mapper.using(managerIdToEmployeeConverter).map(DepartmentRequestDTO::getManagerId, Department::setManager);
            });
    }
}