package com.github.faening.eng_soft_fp_api.domain.model.company;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CompanyRequestDTO implements Serializable {
    private String corporateName;
    private String tradeName;
    private String phone;
    private String email;
    private String addressStreet;
    private String addressNumber;
    private String addressComplement;
    private String addressCity;
    private String addressUF;
    private String addressZipCode;

    /**
     * A lista `unknownFields` é utilizada para armazenar os campos desconhecidos que são enviados no corpo da requisição.
     * Esses campos são ignorados e não são utilizados para atualizar a empresa. Caso algum campo desconhecido seja enviado, uma exceção é lançada.
     */
    private final List<String> unknownFields = new ArrayList<>();

    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        unknownFields.add(key);
    }

    public void validate() {
        if (!unknownFields.isEmpty()) {
            throw new IllegalArgumentException("Os seguintes campos não são permitidos: " + String.join(", ", unknownFields));
        }
    }

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
}