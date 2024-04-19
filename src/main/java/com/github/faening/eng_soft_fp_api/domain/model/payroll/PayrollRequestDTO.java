package com.github.faening.eng_soft_fp_api.domain.model.payroll;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class PayrollRequestDTO implements Serializable {
    private Integer companyId;
    private Integer employeeId;
    private Month month;
    private Integer year;
    private BigDecimal grossSalary;
    private BigDecimal inssBaseAmount;
    private BigDecimal inssTaxRate;
    private BigDecimal irrfBaseAmount;
    private BigDecimal irrfTaxRate;
    private BigDecimal fgtsBaseAmount;
    private BigDecimal fgtsPayed;
    private BigDecimal totalAdditions;
    private BigDecimal totalDiscounts;
    private BigDecimal totalLiquid;
    private List<PayrollItemRequestDTO> items;
    private PaymentStatus status;
    private String notes;

    public PayrollRequestDTO() {
    }

    public PayrollRequestDTO(
        Integer companyId,
        Integer employeeId,
        Month month,
        Integer year,
        BigDecimal grossSalary,
        BigDecimal inssBaseAmount,
        BigDecimal inssTaxRate,
        BigDecimal irrfBaseAmount,
        BigDecimal irrfTaxRate,
        BigDecimal fgtsBaseAmount,
        BigDecimal fgtsPayed,
        BigDecimal totalAdditions,
        BigDecimal totalDiscounts,
        BigDecimal totalLiquid,
        List<PayrollItemRequestDTO> items,
        PaymentStatus status,
        String notes
    ) {
        this.companyId = companyId;
        this.employeeId = employeeId;
        this.month = month;
        this.year = year;
        this.grossSalary = grossSalary;
        this.inssBaseAmount = inssBaseAmount;
        this.inssTaxRate = inssTaxRate;
        this.irrfBaseAmount = irrfBaseAmount;
        this.irrfTaxRate = irrfTaxRate;
        this.fgtsBaseAmount = fgtsBaseAmount;
        this.fgtsPayed = fgtsPayed;
        this.totalAdditions = totalAdditions;
        this.totalDiscounts = totalDiscounts;
        this.totalLiquid = totalLiquid;
        this.items = items;
        this.status = status;
        this.notes = notes;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public BigDecimal getIrrfTaxRate() {
        return irrfTaxRate;
    }

    public void setIrrfTaxRate(BigDecimal irrfTaxRate) {
        this.irrfTaxRate = irrfTaxRate;
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

    public List<PayrollItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<PayrollItemRequestDTO> items) {
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

    @Override
    public String toString() {
        return "PayrollRequestDTO{" +
            "companyId=" + companyId +
            ", employeeId=" + employeeId +
            ", month=" + month +
            ", year=" + year +
            ", grossSalary=" + grossSalary +
            ", inssBaseAmount=" + inssBaseAmount +
            ", inssTaxRate=" + inssTaxRate +
            ", irrfBaseAmount=" + irrfBaseAmount +
            ", irrfTaxRate=" + irrfTaxRate +
            ", fgtsBaseAmount=" + fgtsBaseAmount +
            ", fgtsPayed=" + fgtsPayed +
            ", totalAdditions=" + totalAdditions +
            ", totalDiscounts=" + totalDiscounts +
            ", totalLiquid=" + totalLiquid +
            ", items=" + items +
            ", status=" + status +
            ", notes='" + notes + '\'' +
            '}';
    }
}