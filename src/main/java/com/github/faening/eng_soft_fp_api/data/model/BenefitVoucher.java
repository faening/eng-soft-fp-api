package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BenefitVoucherType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Entity
@Table(name = "benefit_voucher")
public class BenefitVoucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_benefit_voucher")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "paid_value", nullable = false)
    private Double paidValue;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "description", length = 120, nullable = false)
    private String description;

    @Column(name = "benefit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BenefitVoucherType benefitType;

    @Column(name = "payment_status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payroll_deductible", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean payrollDeductible;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public BenefitVoucher() {
    }

    public BenefitVoucher(
        Integer id,
        Employee employee,
        Double paidValue,
        LocalDate releaseDate,
        String description,
        BenefitVoucherType benefitType,
        PaymentStatus paymentStatus,
        Boolean payrollDeductible,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.paidValue = paidValue;
        this.releaseDate = releaseDate;
        this.description = description;
        this.benefitType = benefitType;
        this.paymentStatus = paymentStatus;
        this.payrollDeductible = payrollDeductible;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(Double paidValue) {
        this.paidValue = paidValue;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BenefitVoucherType getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(BenefitVoucherType benefitType) {
        this.benefitType = benefitType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Boolean getPayrollDeductible() {
        return payrollDeductible;
    }

    public void setPayrollDeductible(Boolean payrollDeductible) {
        this.payrollDeductible = payrollDeductible;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}