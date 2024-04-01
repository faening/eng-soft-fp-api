package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

@SuppressWarnings("unused")
@Embeddable
public class Address implements Serializable {
    @Column(name = "address_street", length = 100, nullable = false)
    private String addressStreet;

    @Column(name = "address_number", length = 6, nullable = false)
    private String addressNumber;

    @Column(name = "address_complement", length = 100)
    private String addressComplement;

    @Column(name = "address_city", length = 100, nullable = false)
    private String addressCity;

    @Column(name = "address_uf", nullable = false)
    @Enumerated(EnumType.STRING)
    private BrazilianState addressUF;

    @Column(name = "address_zipCode", length = 8, nullable = false)
    private String addressZipCode;

    public Address() { }

    public Address(String addressStreet, String addressNumber, String addressComplement, String addressCity, BrazilianState addressUF, String addressZipCode) {
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressComplement = addressComplement;
        this.addressCity = addressCity;
        this.addressUF = addressUF;
        this.addressZipCode = addressZipCode;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public BrazilianState getAddressUF() {
        return addressUF;
    }

    public void setAddressUF(BrazilianState addressUF) {
        this.addressUF = addressUF;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }
}