package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Loan;
import com.github.faening.eng_soft_fp_api.data.model.LoanInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanInstallmentRepository extends JpaRepository<LoanInstallment, Integer> {
    List<LoanInstallment> findByLoan(Loan loan);
}