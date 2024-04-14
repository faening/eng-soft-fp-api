package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.TripExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TripExpenseRepository extends JpaRepository<TripExpense, Integer> {
    TripExpense findByEmployeeAndDate(Employee employee, LocalDate date);
}