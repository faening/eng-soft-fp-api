package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.PayrollItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollItemRepository extends JpaRepository<PayrollItem, Integer> { }
