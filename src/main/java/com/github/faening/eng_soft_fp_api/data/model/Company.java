package com.github.faening.eng_soft_fp_api.data.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("unused")
@Entity
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    Integer id;

    @Column(name = "corporate_name", length = 100, nullable = false)
    String corporateName;

    @Column(name = "trade_name", length = 100, nullable = false)
    String tradeName;

    @Column(name = "cnpj", length = 14, nullable = false)
    String cnpj;

    @Column(name = "ie", length = 10, nullable = false)
    String ie;

    @Column(name = "opening_date", nullable = false, columnDefinition = "DEFAULT '2014-01-01'")
    LocalDate openingDate;

    @Column(name = "phone", length = 10)
    String phone;

    @Column(name = "email", length = 50)
    String email;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "addressStreet", column = @Column(name = "address_street")),
        @AttributeOverride(name = "addressNumber", column = @Column(name = "address_number")),
        @AttributeOverride(name = "addressComplement", column = @Column(name = "address_complement")),
        @AttributeOverride(name = "addressCity", column = @Column(name = "address_city")),
        @AttributeOverride(name = "addressUF", column = @Column(name = "address_uf")),
        @AttributeOverride(name = "addressZipCode", column = @Column(name = "address_zip_code"))
    })
    Address address;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    EntityMetadata entityMetadata;

    public Company() { }

    public Company(
        String corporateName,
        String tradeName,
        String cnpj,
        String ie,
        LocalDate openingDate,
        String phone,
        String email,
        Address address,
        EntityMetadata entityMetadata
    ) {
        this.corporateName = corporateName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.ie = ie;
        this.openingDate = openingDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.entityMetadata = entityMetadata;
    }

    public Company(
        Integer id,
        String corporateName,
        String tradeName,
        String cnpj,
        String ie,
        LocalDate openingDate,
        String phone,
        String email,
        Address address,
        EntityMetadata entityMetadata
    ) {
        this.id = id;
        this.corporateName = corporateName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.ie = ie;
        this.openingDate = openingDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.entityMetadata = entityMetadata;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}