package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.EmployeeDependent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDependentRepository extends JpaRepository<EmployeeDependent, Integer> {
    List<EmployeeDependent> findByEmployee(Employee employee);
}
