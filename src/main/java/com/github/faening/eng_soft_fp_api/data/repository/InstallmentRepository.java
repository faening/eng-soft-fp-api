package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.LoanInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<LoanInstallment, Integer> { }
