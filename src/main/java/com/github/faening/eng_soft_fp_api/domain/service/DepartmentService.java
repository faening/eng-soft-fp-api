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

    private Department searchDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum departamento encontrado com o id: " + id)
        );
    }

    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();                     // Busca todos os departamentos
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(departments); // Mapeia a lista de entidades `Department` para a lista de `DepartmentResponseDTO` e a retorna
    }

    public DepartmentResponseDTO getDepartmentById(Integer id) {
        Department department = searchDepartmentById(id);                                 // Busca o departamento pelo id
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(department); // Mapeia a entidade `Department` para o `DepartmentResponseDTO` e o retorna
    }

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {
        departmentRequestDTO.setEnabled(departmentRequestDTO.getEnabled() == null);                                // Define o valor padrão para o campo `enabled`
        Department department = departmentRequestMapper.mapDepartmentRequestDTOToDepartment(departmentRequestDTO); // Mapeia o `DepartmentRequestDTO` para a entidade `Department`
        Department createdDepartment = departmentRepository.save(department);                                      // Salva o departamento no banco de dados
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(createdDepartment);                   // Mapeia a entidade `Department para o `DepartmentResponseDTO` e o retorna
    }

    public DepartmentResponseDTO updateDepartment(Integer id, DepartmentRequestDTO departmentRequestDTO) {
        Department department = searchDepartmentById(id);                                        // Busca o departamento pelo id
        departmentRequestMapper.updateSourceFromDestination(department, departmentRequestDTO);   // Atualiza o departamento com os dados do `DepartmentRequestDTO`
        Department updatedDepartment = departmentRepository.save(department);                    // Salva o departamento no banco de dados
        return departmentResponseMapper.mapDepartmentToDepartmentResponseDTO(updatedDepartment); // Mapeia a entidade `Department para o `DepartmentResponseDTO` e o retorna
    }

    public void deleteDepartment(Integer id) {
        Department department = searchDepartmentById(id); // Busca o departamento pelo id
        departmentRepository.delete(department);          // Deleta o departamento do banco de dados
    }
}