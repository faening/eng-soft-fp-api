package com.github.faening.eng_soft_fp_api.data.repository;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.UnionContribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnionContributionRepository extends JpaRepository<UnionContribution, Integer> {
    UnionContribution findByEmployeeAndReleaseYear(Employee employee, Integer releaseYear);
}