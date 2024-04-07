package com.github.faening.eng_soft_fp_api.domain.model.payroll;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemResponseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unused")
public class PayrollResponseDTO implements Serializable {
    private Integer id;
    private Company company;
    private Employee employee;
    private Month month;
    private Integer year;
    private BigDecimal grossSalary;
    private BigDecimal inssBaseAmount;
    private BigDecimal inssTaxRate;
    private BigDecimal irrfBaseAmount;
    private BigDecimal irrTaxRate;
    private BigDecimal fgtsBaseAmount;
    private BigDecimal fgtsPayed;
    private BigDecimal totalAdditions;
    private BigDecimal totalDiscounts;
    private BigDecimal totalLiquid;
    private List<PayrollItemResponseDTO> items;
    private PaymentStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PayrollResponseDTO() {
    }

    public PayrollResponseDTO(
        Integer id,
        Company company,
        Employee employee,
        Month month,
        Integer year,
        BigDecimal grossSalary,
        BigDecimal inssBaseAmount,
        BigDecimal inssTaxRate,
        BigDecimal irrfBaseAmount,
        BigDecimal irrTaxRate,
        BigDecimal fgtsBaseAmount,
        BigDecimal fgtsPayed,
        BigDecimal totalAdditions,
        BigDecimal totalDiscounts,
        BigDecimal totalLiquid,
        List<PayrollItemResponseDTO> items,
        PaymentStatus status,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.company = company;
        this.employee = employee;
        this.month = month;
        this.year = year;
        this.grossSalary = grossSalary;
        this.inssBaseAmount = inssBaseAmount;
        this.inssTaxRate = inssTaxRate;
        this.irrfBaseAmount = irrfBaseAmount;
        this.irrTaxRate = irrTaxRate;
        this.fgtsBaseAmount = fgtsBaseAmount;
        this.fgtsPayed = fgtsPayed;
        this.totalAdditions = totalAdditions;
        this.totalDiscounts = totalDiscounts;
        this.totalLiquid = totalLiquid;
        this.items = items;
        this.status = status;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public BigDecimal getInssBaseAmount() {
        return inssBaseAmount;
    }

    public void setInssBaseAmount(BigDecimal inssBaseAmount) {
        this.inssBaseAmount = inssBaseAmount;
    }

    public BigDecimal getInssTaxRate() {
        return inssTaxRate;
    }

    public void setInssTaxRate(BigDecimal inssTaxRate) {
        this.inssTaxRate = inssTaxRate;
    }

    public BigDecimal getIrrfBaseAmount() {
        return irrfBaseAmount;
    }

    public void setIrrfBaseAmount(BigDecimal irrfBaseAmount) {
        this.irrfBaseAmount = irrfBaseAmount;
    }

    public BigDecimal getIrrTaxRate() {
        return irrTaxRate;
    }

    public void setIrrTaxRate(BigDecimal irrTaxRate) {
        this.irrTaxRate = irrTaxRate;
    }

    public BigDecimal getFgtsBaseAmount() {
        return fgtsBaseAmount;
    }

    public void setFgtsBaseAmount(BigDecimal fgtsBaseAmount) {
        this.fgtsBaseAmount = fgtsBaseAmount;
    }

    public BigDecimal getFgtsPayed() {
        return fgtsPayed;
    }

    public void setFgtsPayed(BigDecimal fgtsPayed) {
        this.fgtsPayed = fgtsPayed;
    }

    public BigDecimal getTotalAdditions() {
        return totalAdditions;
    }

    public void setTotalAdditions(BigDecimal totalAdditions) {
        this.totalAdditions = totalAdditions;
    }

    public BigDecimal getTotalDiscounts() {
        return totalDiscounts;
    }

    public void setTotalDiscounts(BigDecimal totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    public BigDecimal getTotalLiquid() {
        return totalLiquid;
    }

    public void setTotalLiquid(BigDecimal totalLiquid) {
        this.totalLiquid = totalLiquid;
    }

    public List<PayrollItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<PayrollItemResponseDTO> items) {
        this.items = items;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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