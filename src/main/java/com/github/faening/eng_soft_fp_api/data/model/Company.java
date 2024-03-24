package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "company")
public record Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    Integer id,

    @Column(name = "corporate_name", length = 100, nullable = false)
    String corporateName,

    @Column(name = "trade_name", length = 100, nullable = false)
    String tradeName,

    @Column(name = "cnpj", length = 14, nullable = false)
    String cnpj,

    @Column(name = "ie", length = 10, nullable = false)
    String ie,

    @Column(name = "opening_date")
    @Temporal(TemporalType.DATE)
    Date openingDate,

    @Column(name = "phone", length = 10)
    String phone,

    @Column(name = "email", length = 50)
    String email,

    @Embedded
    Address address,

    @Embedded
    EntityMetadata entityMetadata
) implements Serializable { }