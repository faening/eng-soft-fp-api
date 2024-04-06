package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "financial_event")
public class FinancialEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financial_event")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "rubric_id", referencedColumnName = "id_rubric", nullable = false)
    private Rubric rubric;

    @Column(name = "value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @OneToMany(mappedBy = "financialEvent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Installment> installments;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public FinancialEvent() {
    }

    public FinancialEvent(
        Integer id,
        Employee employee,
        Rubric rubric,
        BigDecimal value,
        LocalDate releaseDate,
        ApprovalStatus status,
        List<Installment> installments,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.employee = employee;
        this.rubric = rubric;
        this.value = value;
        this.releaseDate = releaseDate;
        this.status = status;
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

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}