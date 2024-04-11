package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.HoursWorkedSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HoursWorkedSheetRepository extends JpaRepository<HoursWorkedSheet, Integer> {
    List<HoursWorkedSheet> findByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);
}