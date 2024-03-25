package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "financial_event")
public record FinancialEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financial_event")
    Integer id,

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @ManyToOne
    @JoinColumn(name = "rubric_id", referencedColumnName = "id_rubric", nullable = false)
    Rubric rubric,

    @Column(name = "value", nullable = false)
    Double value,

    @Column(name = "release_date", nullable = false)
    @Temporal(TemporalType.DATE)
    Date releaseDate,

    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    ApprovalStatus status,

    @OneToMany(mappedBy = "financialEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Installment> installments,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
