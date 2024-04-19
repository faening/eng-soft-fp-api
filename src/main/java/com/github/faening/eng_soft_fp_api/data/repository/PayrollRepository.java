package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Payroll;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {
    Payroll findByEmployeeAndMonthAndYear(Employee employee, Month month, Integer year);
}
