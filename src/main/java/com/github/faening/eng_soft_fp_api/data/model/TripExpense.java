package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Entity
@Table(name = "trip_expense")
public class TripExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trip_expense")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "description", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public TripExpense() {
    }

    public TripExpense(
        Integer id,
        Employee employee,
        LocalDate date,
        BigDecimal amount,
        ApprovalStatus status,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.date = date;
        this.amount = amount;
        this.status = status;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}