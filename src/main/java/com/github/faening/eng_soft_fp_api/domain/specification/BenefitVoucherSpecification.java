package com.github.faening.eng_soft_fp_api.domain.specification;

import com.github.faening.eng_soft_fp_api.data.model.BenefitVoucher;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@SuppressWarnings("unused")
public class BenefitVoucherSpecification implements Specification<BenefitVoucher> {
    private final Employee employee;
    private final LocalDate releaseDate;
    private final BenefitVoucherType benefitType;
    private final PaymentStatus paymentStatus;
    private final Boolean payrollDeductible;

    public BenefitVoucherSpecification(
        Employee employee,
        LocalDate releaseDate,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible
    ) {
        this.employee = employee;
        this.releaseDate = releaseDate;
        this.benefitType = benefitType;
        this.paymentStatus = paymentStatus;
        this.payrollDeductible = payrollDeductible;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Predicate toPredicate(Root<BenefitVoucher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get("employee"), this.employee);

        if (releaseDate != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("releaseDate"), this.releaseDate));
        }

        if (benefitType != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("benefitType"), this.benefitType));
        }

        if (paymentStatus != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("paymentStatus"), this.paymentStatus));
        }


        if (payrollDeductible != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("payrollDeductible"), this.payrollDeductible));
        }

        return predicate;
    }
}