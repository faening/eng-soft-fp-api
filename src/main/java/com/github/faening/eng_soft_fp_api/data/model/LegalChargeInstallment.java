package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;


@SuppressWarnings("unused")
@Entity
@Table(name = "legal_charge_installment")
public class LegalChargeInstallment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_legal_charge_installment")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "legal_charge_id", referencedColumnName = "id_legal_charge", nullable = false)
    private LegalCharge legalCharge;

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

    public LegalChargeInstallment() {
    }

    public LegalChargeInstallment(
        Integer id,
        LegalCharge legalCharge,
        Installment installment,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.legalCharge = legalCharge;
        this.installment = installment;
        this.entityMetadata = entityMetadata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LegalCharge getLegalCharge() {
        return legalCharge;
    }

    public void setLegalCharge(LegalCharge legalCharge) {
        this.legalCharge = legalCharge;
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