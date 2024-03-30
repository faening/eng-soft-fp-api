package com.github.faening.eng_soft_fp_api.domain.model.company;

import java.io.Serializable;

@SuppressWarnings("unused")
public class CompanyRequestDTO implements Serializable {
    private String corporateName;
    private String tradeName;
    private String phone;
    private String email;

    // Address
    private String addressStreet;
    private String addressNumber;
    private String addressComplement;
    private String addressCity;
    private String addressUF;
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
        String addressComplement,
        String addressCity,
        String addressUF,
        String addressZipCode
    ) {
        this.corporateName = corporateName;
        this.tradeName = tradeName;
        this.phone = phone;
        this.email = email;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
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

    public String getAddressUF() {
        return this.addressUF;
    }

    public void setAddressUF(String addressUF) {
        this.addressUF = addressUF;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public boolean isEmpty() {
        return
            corporateName == null &&
            tradeName == null &&
            phone == null &&
            email == null &&
            addressStreet == null &&
            addressNumber == null &&
            addressComplement == null &&
            addressCity == null &&
            addressUF == null &&
            addressZipCode == null;
    }
}