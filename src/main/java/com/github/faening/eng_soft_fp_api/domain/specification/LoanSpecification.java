package com.github.faening.eng_soft_fp_api.domain.specification;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Loan;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class LoanSpecification implements Specification<Loan> {
    private final Employee employee;
    private final LocalDate requestDate;
    private final LocalDate approvalDate;
    private final LocalDate companyPaymentDate;
    private final PaymentStatus paymentStatus;

    public LoanSpecification(
        Employee employee,
        LocalDate requestDate,
        LocalDate approvalDate,
        LocalDate companyPaymentDate,
        PaymentStatus paymentStatus
    ) {
        this.employee = employee;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.companyPaymentDate = companyPaymentDate;
        this.paymentStatus = paymentStatus;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get("employee"), this.employee);

        if (requestDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("requestDate"), this.requestDate));
        }

        if (approvalDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("approvalDate"), this.approvalDate));
        }

        if (companyPaymentDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("companyPaymentDate"), this.companyPaymentDate));
        }

        if (paymentStatus != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("paymentStatus"), this.paymentStatus));
        }

        return predicate;
    }
}