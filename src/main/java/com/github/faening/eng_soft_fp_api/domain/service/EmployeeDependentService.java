package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.EmployeeDependent;
import com.github.faening.eng_soft_fp_api.data.repository.EmployeeDependentRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee_dependent.EmployeeDependentRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee_dependent.EmployeeDependentResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class EmployeeDependentService extends AbstractService<EmployeeDependentRequestDTO, EmployeeDependentResponseDTO>{
    private final EmployeeDependentRepository employeeDependentRepository;
    private final EmployeeDependentRequestMapper employeeDependentRequestMapper;
    private final EmployeeDependentResponseMapper employeeDependentResponseMapper;

    @Autowired
    public EmployeeDependentService(
        EmployeeDependentRepository employeeDependentRepository,
        EmployeeDependentRequestMapper employeeDependentRequestMapper,
        EmployeeDependentResponseMapper employeeDependentResponseMapper
    ) {
        this.employeeDependentRepository = employeeDependentRepository;
        this.employeeDependentRequestMapper = employeeDependentRequestMapper;
        this.employeeDependentResponseMapper = employeeDependentResponseMapper;
    }

    @Override
    public List<EmployeeDependentResponseDTO> getAll() {
        return employeeDependentRepository
            .findAll()
            .stream()
            .map(employeeDependent -> employeeDependentResponseMapper.toDTO(employeeDependent, EmployeeDependentResponseDTO.class))
            .toList();
    }

    @Override
    public EmployeeDependentResponseDTO getById(Integer id) {
        validateId(id);
        return employeeDependentResponseMapper.toDTO(searchEmployeeDependentById(id), EmployeeDependentResponseDTO.class);
    }

    @Override
    public EmployeeDependentResponseDTO create(EmployeeDependentRequestDTO request) {
        validateEmployeeDependentRequestDTO(request);
        EmployeeDependent employeeDependent = employeeDependentRequestMapper.toEntity(request, EmployeeDependent.class);
        EmployeeDependent savedEmployeeDependent = employeeDependentRepository.save(employeeDependent);
        return employeeDependentResponseMapper.toDTO(savedEmployeeDependent, EmployeeDependentResponseDTO.class);
    }

    @Override
    public EmployeeDependentResponseDTO update(Integer id, EmployeeDependentRequestDTO request) {
        validateId(id);
        validateEmployeeDependentRequestDTO(request);
        EmployeeDependent employeeDependent = searchEmployeeDependentById(id);
        employeeDependentRequestMapper.updateSourceFromDestination(employeeDependent, request);
        return employeeDependentResponseMapper.toDTO(employeeDependentRepository.save(employeeDependent), EmployeeDependentResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        employeeDependentRepository.deleteById(id);
    }

    public EmployeeDependent searchEmployeeDependentById(Integer id) {
        return employeeDependentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum dependente encontrado com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.employeeDependentId"));
    }

    private void validateEmployeeDependentRequestDTO(EmployeeDependentRequestDTO request) {
        if (request.getEmployeeId() == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.employeeId"));
        if (request.getName() == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.name"));
        if (request.getRg() == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.rg"));
        if (request.getCpf() == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.cpf"));
        if (request.getBirthDate() == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.birthDate"));
        if (request.getGender() == null) throw new ResourceNotFoundException(getLocalizedMessage("employeeDependentService.validation.gender"));
        if (request.getSpecialNeeds() == null) request.setSpecialNeeds(false);
        if (request.getFamilyAllowance() == null) request.setSpecialNeeds(false);
        if (request.getDaycareAllowance() == null) request.setSpecialNeeds(false);
    }
}