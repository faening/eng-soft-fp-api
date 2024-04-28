package com.github.faening.eng_soft_fp_api.domain.specification;

import com.github.faening.eng_soft_fp_api.data.model.AbsenceSheet;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.enumeration.AbsenceType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class AbsenceSheetSpecification implements Specification<AbsenceSheet> {
    private final Employee employee;
    private final AbsenceType type;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AbsenceSheetSpecification(
        Employee employee,
        AbsenceType type,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {
        this.employee = employee;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Predicate toPredicate(Root<AbsenceSheet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get("employee"), this.employee);

        if (type != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), this.type));
        }

        if (startDate != null && endDate != null) {
            Predicate startDatePredicate = criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), this.startDate),
                    criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), this.endDate)
            );

            Predicate endDatePredicate = criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), this.startDate),
                    criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), this.endDate)
            );

            predicate = criteriaBuilder.and(predicate, startDatePredicate, endDatePredicate);
        }

        return predicate;
    }
}