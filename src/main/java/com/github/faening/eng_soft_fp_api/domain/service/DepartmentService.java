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

@SuppressWarnings("unused")
@Service
public class DepartmentService extends AbstractService<DepartmentRequestDTO, DepartmentResponseDTO> {
    private final DepartmentRepository repository;
    private final DepartmentRequestMapper requestMapper;
    private final DepartmentResponseMapper responseMapper;

    private static final String VALIDATION_MESSAGE_DESCRIPTION = "departmentService.validation.description";
    private static final String VALIDATION_MESSAGE_MANAGER_ID = "departmentService.validation.managerId";

    @Autowired
    public DepartmentService(
        DepartmentRepository repository,
        DepartmentRequestMapper requestMapper,
        DepartmentResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<DepartmentResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(department -> responseMapper.toDTO(department, DepartmentResponseDTO.class))
            .toList();
    }

    @Override
    public DepartmentResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchDepartmentEntityById(id), DepartmentResponseDTO.class);
    }

    public Department getEntityById(Integer id) {
        validate(id);
        return searchDepartmentEntityById(id);
    }

    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO request) {
        validate(request);
        Department department = requestMapper.toEntity(request, Department.class);
        Department savedDepartment = repository.save(department);
        return responseMapper.toDTO(savedDepartment, DepartmentResponseDTO.class);
    }

    @Override
    public DepartmentResponseDTO update(Integer id, DepartmentRequestDTO request) {
        validate(id);
        validate(request);
        Department department = searchDepartmentEntityById(id);
        requestMapper.updateSourceFromDestination(department, request);
        Department updatedDepartment = repository.save(department);
        return responseMapper.toDTO(updatedDepartment, DepartmentResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private Department searchDepartmentEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(DepartmentRequestDTO request) {
        super.validate(request);
        if (request.getDescription() == null || request.getDescription().isEmpty())  throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_DESCRIPTION));
        if (request.getManagerId() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_MANAGER_ID));
        if (request.getEnabled() == null) request.setEnabled(true);
    }
}