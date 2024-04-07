package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@Table(name = "loan_installment")
public class LoanInstallment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan_installment")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id", referencedColumnName = "id_loan", nullable = false)
    private Loan loan;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "installmentNumber", column = @Column(name = "installment_number")),
        @AttributeOverride(name = "installmentValue", column = @Column(name = "installment_value")),
        @AttributeOverride(name = "discountMonth", column = @Column(name = "discount_month")),
        @AttributeOverride(name = "paymentStatus", column = @Column(name = "payment_status"))
    })
    private Installment installment;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public LoanInstallment() {
    }

    public LoanInstallment(
        Integer id,
        Loan loan,
        Installment installment,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.loan = loan;
        this.installment = installment;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Installment getInstallment() {
        return installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}