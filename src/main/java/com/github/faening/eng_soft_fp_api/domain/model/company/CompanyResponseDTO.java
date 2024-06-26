package com.github.faening.eng_soft_fp_api.domain.model.company;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class CompanyResponseDTO implements Serializable {
    private Integer id;
    private String corporateName;
    private String tradeName;
    private String cnpj;
    private String ie;
    private LocalDate openingDate;
    private String phone;
    private String email;
    private String addressStreet;
    private String addressNumber;
    private String addressNeighborhood;
    private String addressComplement;
    private String addressCity;
    private String addressUF;
    private String addressZipCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CompanyResponseDTO() {
    }

    public CompanyResponseDTO(
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
        String addressNeighborhood,
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
        this.addressNeighborhood = addressNeighborhood;
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