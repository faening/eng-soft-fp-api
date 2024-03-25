package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

@Embeddable
public record Address(
    @Column(name = "address_street", length = 100, nullable = false)
    String addressStreet,

    @Column(name = "address_number", length = 6, nullable = false)
    String addressNumber,

    @Column(name = "address_complement", length = 100)
    String addressComplement,

    @Column(name = "address_city", length = 100, nullable = false)
    String addressCity,

    @Column(name = "address_uf", nullable = false)
    @Enumerated(EnumType.STRING)
    BrazilianState addressUF,

    @Column(name = "address_zipCode", length = 8, nullable = false)
    String addressZipCode
) implements Serializable { }