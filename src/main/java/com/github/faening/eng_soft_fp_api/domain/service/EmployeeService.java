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
    private final EmployeeRepository employeeRepository;
    private final EmployeeSummaryMapper employeeSummaryMapper;

    @Autowired
    public EmployeeService(
        EmployeeRepository employeeRepository,
        EmployeeSummaryMapper employeeSummaryMapper
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeSummaryMapper = employeeSummaryMapper;
    }

    public Employee searchEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum funcionário encontrado com o id: " + id)
        );
    }

    public EmployeeSummaryDTO getEmployeeSummayById(Integer id) {
        Employee employee = searchEmployeeById(id);
        return employeeSummaryMapper.toDTO(employee, EmployeeSummaryDTO.class);
    }
}
