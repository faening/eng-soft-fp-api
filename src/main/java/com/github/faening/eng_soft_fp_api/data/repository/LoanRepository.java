package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoanRepository extends JpaRepository<Loan, Integer>, JpaSpecificationExecutor<Loan> { }