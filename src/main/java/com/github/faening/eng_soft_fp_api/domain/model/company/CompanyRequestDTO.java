package com.github.faening.eng_soft_fp_api.domain.model.company;

import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;

import java.io.Serializable;

@SuppressWarnings("unused")
public class CompanyRequestDTO implements Serializable {
    private String corporateName;
    private String tradeName;
    private String phone;
    private String email;
    private String addressStreet;
    private String addressNumber;
    private String addressNeighborhood;
    private String addressComplement;
    private String addressCity;
    private BrazilianState addressUF;
    private String addressZipCode;

    public CompanyRequestDTO() {
    }

    public CompanyRequestDTO(
        String corporateName,
        String tradeName,
        String phone,
        String email,
        String addressStreet,
        String addressNumber,
        String addressNeighborhood,
        String addressComplement,
        String addressCity,
        BrazilianState addressUF,
        String addressZipCode
    ) {
        this.corporateName = corporateName;
        this.tradeName = tradeName;
        this.phone = phone;
        this.email = email;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.addressComplement = addressComplement;
        this.addressCity = addressCity;
        this.addressUF = addressUF;
        this.addressZipCode = addressZipCode;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddressNeighborhood() {
        return addressNeighborhood;
    }

    public void setAddressNeighborhood(String addressNeighborhood) {
        this.addressNeighborhood = addressNeighborhood;
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