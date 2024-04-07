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
    private List<LoanInstallment> installments;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Loan() {
    }

    public Loan(
        Integer id,
        Employee employee,
        BigDecimal loanAmountValue,
        Integer installmentQuantity,
        LocalDate requestDate,
        LocalDate approvalDate,
        LocalDate companyPaymentDate,
        PaymentStatus paymentStatus,
        List<LoanInstallment> installments,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.loanAmountValue = loanAmountValue;
        this.installmentQuantity = installmentQuantity;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
        this.companyPaymentDate = companyPaymentDate;
        this.paymentStatus = paymentStatus;
        this.installments = installments;
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

    public BigDecimal getLoanAmountValue() {
        return loanAmountValue;
    }

    public void setLoanAmountValue(BigDecimal loanAmountValue) {
        this.loanAmountValue = loanAmountValue;
    }

    public Integer getInstallmentQuantity() {
        return installmentQuantity;
    }

    public void setInstallmentQuantity(Integer installmentQuantity) {
        this.installmentQuantity = installmentQuantity;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getCompanyPaymentDate() {
        return companyPaymentDate;
    }

    public void setCompanyPaymentDate(LocalDate companyPaymentDate) {
        this.companyPaymentDate = companyPaymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<LoanInstallment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LoanInstallment> installments) {
        this.installments = installments;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}