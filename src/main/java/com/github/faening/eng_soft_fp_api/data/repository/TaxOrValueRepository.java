package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.TaxOrValue;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxOrValueRepository extends JpaRepository<TaxOrValue, Integer> {
    List<TaxOrValue> findByType(TaxOrValueType type);
}
