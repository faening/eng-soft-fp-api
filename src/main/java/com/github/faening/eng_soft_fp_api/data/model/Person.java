package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public record Person(
    @Column(name = "name", length = 120, nullable = false)
    String name,

    @Column(name = "rg", length = 11, nullable = false)
    String rg,

    @Column(name = "cpf", length = 11, nullable = false)
    String cpf,

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    Date birthDate,

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender
) implements Serializable { }
