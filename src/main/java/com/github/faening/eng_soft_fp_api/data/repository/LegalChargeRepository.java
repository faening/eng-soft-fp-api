package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LegalChargeRepository extends JpaRepository<LegalCharge, Integer>, JpaSpecificationExecutor<LegalCharge> { }