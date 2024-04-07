package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "loan")
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "loan_amount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal loanAmountValue;

    @Column(name = "installment_quantity", nullable = false)
    private Integer installmentQuantity;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Column(name = "company_payment_date")
    private LocalDate companyPaymentDate;

    @Column(name = "company_payment_status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Installment> installments;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Loan() {
    }


}