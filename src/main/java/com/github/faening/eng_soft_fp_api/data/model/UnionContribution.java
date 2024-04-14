package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.*;

import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@Table(name = "union_contribution")
public class UnionContribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_union_contribution")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "opted_out", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean optedOut;

    @Column(name = "payment_status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public UnionContribution() {
    }

    public UnionContribution(
        Integer id,
        Employee employee,
        Integer releaseYear,
        Boolean optedOut,
        PaymentStatus paymentStatus,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.releaseYear = releaseYear;
        this.optedOut = optedOut;
        this.paymentStatus = paymentStatus;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean getOptedOut() {
        return optedOut;
    }

    public void setOptedOut(Boolean optedOut) {
        this.optedOut = optedOut;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}