package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.BenefitVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BenefitVoucherRepository extends JpaRepository<BenefitVoucher, Integer>, JpaSpecificationExecutor<BenefitVoucher> { }
