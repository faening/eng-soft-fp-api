package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.repository.EmployeeRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.employee.EmployeeSummaryMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee.EmployeeSummaryDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeSummaryMapper employeeSummaryMapper;

    @Autowired
    public EmployeeService(
        EmployeeRepository repository,
        EmployeeSummaryMapper employeeSummaryMapper
    ) {
        this.repository = repository;
        this.employeeSummaryMapper = employeeSummaryMapper;
    }

    public Employee getEntityById(Integer id) {
        return searchEmployeeById(id);
    }

    public EmployeeSummaryDTO getSummaryById(Integer id) {
        Employee employee = searchEmployeeById(id);
        return employeeSummaryMapper.toDTO(employee, EmployeeSummaryDTO.class);
    }

    private Employee searchEmployeeById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum funcionário encontrado com o id: " + id)
        );
    }
}
