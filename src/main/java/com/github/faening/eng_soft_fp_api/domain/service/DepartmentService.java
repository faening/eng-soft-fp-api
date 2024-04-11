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

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class DepartmentService extends AbstractService<DepartmentRequestDTO, DepartmentResponseDTO> {
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

    @Override
    public List<DepartmentResponseDTO> getAll() {
        return departmentRepository
            .findAll()
            .stream()
            .map(department -> departmentResponseMapper.toDTO(department, DepartmentResponseDTO.class))
            .toList();
    }

    @Override
    public DepartmentResponseDTO getById(Integer id) {
        validateId(id);
        return departmentResponseMapper.toDTO(searchDepartmentById(id), DepartmentResponseDTO.class);
    }

    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO request) {
        validateDepartmentRequestDTO(request);
        Department department = departmentRequestMapper.toEntity(request, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return departmentResponseMapper.toDTO(savedDepartment, DepartmentResponseDTO.class);
    }

    @Override
    public DepartmentResponseDTO update(Integer id, DepartmentRequestDTO request) {
        validateId(id);
        validateDepartmentRequestDTO(request);
        Department department = searchDepartmentById(id);
        departmentRequestMapper.updateSourceFromDestination(department, request);
        Department updatedDepartment = departmentRepository.save(department);
        return departmentResponseMapper.toDTO(updatedDepartment, DepartmentResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        departmentRepository.deleteById(id);
    }

    public Department searchDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum departamento encontrado com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage("departmentService.validation.departmentId"));
    }

    private void validateDepartmentRequestDTO(DepartmentRequestDTO departmentRequestDTO) {
        if (departmentRequestDTO.getDescription() == null || departmentRequestDTO.getDescription().isEmpty())
            throw new IllegalArgumentException(getLocalizedMessage("departmentService.validation.description"));
        if (departmentRequestDTO.getManagerId() == null)
            throw new IllegalArgumentException(getLocalizedMessage("departmentService.validation.managerId"));
        if (departmentRequestDTO.getEnabled() == null) departmentRequestDTO.setEnabled(true);
    }
}