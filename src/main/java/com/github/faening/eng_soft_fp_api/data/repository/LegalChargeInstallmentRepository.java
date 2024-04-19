package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import com.github.faening.eng_soft_fp_api.data.model.LegalChargeInstallment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalChargeInstallmentRepository extends JpaRepository<LegalChargeInstallment, Integer> {
    List<LegalChargeInstallment> findByLegalCharge(LegalCharge legalCharge);
}