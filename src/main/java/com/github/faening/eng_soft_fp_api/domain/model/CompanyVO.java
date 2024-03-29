package com.github.faening.eng_soft_fp_api.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class CompanyVO {
    Integer id;
    String corporateName;
    String tradeName;
    String cnpj;
    String ie;
    LocalDate openingDate;
    String phone;
    String email;

    // Address
    String addressStreet;
    String addressNumber;
    String addressComplement;
    String addressCity;
    String addressUF;
    String addressZipCode;

    // EntityMetadata
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public CompanyVO() {
    }

    public CompanyVO(
        Integer id,
        String corporateName,
        String tradeName,
        String cnpj,
        String ie,
        LocalDate openingDate,
        String phone,
        String email,
        String addressStreet,
        String addressNumber,
        String addressComplement,
        String addressCity,
        String addressUF,
        String addressZipCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.corporateName = corporateName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.ie = ie;
        this.openingDate = openingDate;
        this.phone = phone;
        this.email = email;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressComplement = addressComplement;
        this.addressCity = addressCity;
        this.addressUF = addressUF;
        this.addressZipCode = addressZipCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CompanyVO(
        String corporateName,
        String tradeName,
        String cnpj,
        String ie,
        LocalDate openingDate,
        String phone,
        String email,
        String addressStreet,
        String addressNumber,
        String addressComplement,
        String addressCity,
        String addressUF,
        String addressZipCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.corporateName = corporateName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.ie = ie;
        this.openingDate = openingDate;
        this.phone = phone;
        this.email = email;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressComplement = addressComplement;
        this.addressCity = addressCity;
        this.addressUF = addressUF;
        this.addressZipCode = addressZipCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
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
        return addressUF;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}