package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "legal_charge")
public class LegalCharge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_legal_charge")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "legal_charge_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal legalChargeAmount;

    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "is_recurring", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isRecurring;

    @OneToMany(mappedBy = "legalCharge", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<LegalChargeInstallment> installments;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public LegalCharge() {
    }

    public LegalCharge(
        Integer id,
        Employee employee,
        BigDecimal legalChargeAmount,
        BigDecimal percentage,
        LocalDate releaseDate,
        Boolean isRecurring,
        List<LegalChargeInstallment> installments,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.legalChargeAmount = legalChargeAmount;
        this.percentage = percentage;
        this.releaseDate = releaseDate;
        this.isRecurring = isRecurring;
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

    public BigDecimal getLegalChargeAmount() {
        return legalChargeAmount;
    }

    public void setLegalChargeAmount(BigDecimal legalChargeAmount) {
        this.legalChargeAmount = legalChargeAmount;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean recurring) {
        isRecurring = recurring;
    }

    public List<LegalChargeInstallment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<LegalChargeInstallment> installments) {
        this.installments = installments;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}