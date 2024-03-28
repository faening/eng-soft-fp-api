package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "benefit_voucher")
public record BenefitVoucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_benefit_voucher")
    Integer id,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Column(name = "paid_value", nullable = false)
    Double paidValue,

    @Column(name = "release_date", nullable = false)
    LocalDate releaseDate,

    @Column(name = "description", length = 120, nullable = false)
    String description,

    @Column(name = "benefit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    BenefitVoucherType benefitType,

    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    ApprovalStatus status,

    @Column(name = "payroll_deductible", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    Boolean payrollDeductible,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }