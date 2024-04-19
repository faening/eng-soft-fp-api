package com.github.faening.eng_soft_fp_api.domain.specification;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class LegalChargeSpecification implements Specification<LegalCharge> {
    private final Employee employee;
    private final BigDecimal legalChargeAmount;
    private final BigDecimal percentage;
    private final LocalDate releaseDate;
    private final Boolean isRecurring;

    public LegalChargeSpecification(
        Employee employee,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring
    ) {
        this.employee = employee;
        this.legalChargeAmount = legalChargeAmount;
        this.percentage = percentage;
        this.releaseDate = releaseDate;
        this.isRecurring = isRecurring;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Predicate toPredicate(Root<LegalCharge> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get("employee"), this.employee);

        if (legalChargeAmount != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("legalChargeAmount"), this.legalChargeAmount));
        }

        if (percentage != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("percentage"), this.percentage));
        }

        if (releaseDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("releaseDate"), this.releaseDate));
        }

        if (isRecurring != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("isRecurring"), this.isRecurring));
        }

        return predicate;
    }
}