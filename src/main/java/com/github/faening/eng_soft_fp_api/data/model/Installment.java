package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("unused")
@Entity
@Table(name = "installment")
public class Installment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_installment")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id", referencedColumnName = "id_loan", nullable = false)
    private Loan loan;

    @Column(name = "installment_number", nullable = false)
    private Integer installmentNumber;

    @Column(name = "installment_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal installmentValue;

    @Column(name = "discount_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month discountMonth;

    @Column(name = "payment_status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Installment() {
    }


}