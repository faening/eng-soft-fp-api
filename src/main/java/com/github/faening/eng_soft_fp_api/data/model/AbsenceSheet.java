package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.AbsenceType;
import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "absence_sheet")
public record AbsenceSheet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_absence_sheet")
    Integer id,

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    Employee employee,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    AbsenceType type,

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date startDate,

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date endDate,

    @Column(name = "observation")
    String observation,

    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    ApprovalStatus status,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }
