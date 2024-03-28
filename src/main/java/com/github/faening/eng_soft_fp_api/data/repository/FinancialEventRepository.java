package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.FinancialEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialEventRepository extends JpaRepository<FinancialEvent, Integer> { }
