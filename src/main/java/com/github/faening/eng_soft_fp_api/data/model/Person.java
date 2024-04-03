package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Embeddable
public class Person implements Serializable {
    @Column(name = "name", length = 120, nullable = false)
    private String name;

    @Column(name = "rg", length = 11, nullable = false)
    private String rg;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Person() {
    }

    public Person(String name, String rg, String cpf, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.rg = rg;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}