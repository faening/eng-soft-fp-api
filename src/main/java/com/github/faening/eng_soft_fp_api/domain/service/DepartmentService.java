package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Department;
import com.github.faening.eng_soft_fp_api.data.repository.DepartmentRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.department.DepartmentRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.department.DepartmentResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.department.DepartmentResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentRequestMapper departmentRequestMapper;
    private final DepartmentResponseMapper departmentResponseMapper;

    @Autowired
    public DepartmentService(
        DepartmentRepository departmentRepository,
        DepartmentRequestMapper departmentRequestMapper,
        DepartmentResponseMapper departmentResponseMapper
    ) {
        this.departmentRepository = departmentRepository;
        this.departmentRequestMapper = departmentRequestMapper;
        this.departmentResponseMapper = departmentResponseMapper;
    }

    public Department searchDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum departamento encontrado com o id: " + id)
        );
    }

    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(departments);
    }

    public DepartmentResponseDTO getDepartmentById(Integer id) {
        Department department = searchDepartmentById(id);
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(department);
    }

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        validateDepartmentRequestDTO(departmentRequestDTO);
        Department department = departmentRequestMapper.mapDepartmentRequestDTOToDepartment(departmentRequestDTO);
        Department createdDepartment = departmentRepository.save(department);
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(createdDepartment);
    }

    public DepartmentResponseDTO updateDepartment(Integer id, DepartmentRequestDTO departmentRequestDTO) {
        validateDepartmentRequestDTO(departmentRequestDTO);
        Department department = searchDepartmentById(id);
        departmentRequestMapper.updateSourceFromDestination(department, departmentRequestDTO);
        Department updatedDepartment = departmentRepository.save(department);
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(updatedDepartment);
    }

    public void deleteDepartment(Integer id) {
        Department department = searchDepartmentById(id);
        departmentRepository.delete(department);
    }

    private void validateDepartmentRequestDTO(DepartmentRequestDTO departmentRequestDTO) {
        if (departmentRequestDTO.getDescription() == null || departmentRequestDTO.getDescription().isEmpty()) throw new IllegalArgumentException("Description is required");
        if (departmentRequestDTO.getManagerId() == null) throw new IllegalArgumentException("Manager ID is required");
        if (departmentRequestDTO.getEnabled() == null) departmentRequestDTO.setEnabled(true);
    }
}