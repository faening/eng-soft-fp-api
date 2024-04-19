package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.TripExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripExpenseRepository extends JpaRepository<TripExpense, Integer> {
    List<TripExpense> findByEmployeeAndDateBetween(Employee employee, LocalDate startDate, LocalDate endDate);
    TripExpense findByEmployeeAndDate(Employee employee, LocalDate date);
}