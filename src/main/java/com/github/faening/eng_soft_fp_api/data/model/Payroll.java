package com.github.faening.eng_soft_fp_api.data.model;

import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Entity
@Table(name = "payroll")
public class Payroll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payroll")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id_company", nullable = false, columnDefinition = "DEFAULT 1")
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id_employee", nullable = false)
    private Employee employee;

    @Column(name = "month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "gross_salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal grossSalary;

    @Column(name = "inss_base_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal inssBaseAmount;

    @Column(name = "inss_tax_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal inssTaxRate;

    @Column(name = "irrf_base_amout", nullable = false, precision = 10, scale = 2)
    private BigDecimal irrfBaseAmount;

    @Column(name = "irrf_tax_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal irrfTaxRate;

    @Column(name = "fgts_base_amout", nullable = false, precision = 10, scale = 2)
    private BigDecimal fgtsBaseAmount;

    @Column(name = "fgts_payed", nullable = false, precision = 10, scale = 2)
    private BigDecimal fgtsPayed;

    @Column(name = "total_additions", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAdditions;

    @Column(name = "total_discounts", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDiscounts;

    @Column(name = "total_liquid", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalLiquid;

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PayrollItem> items;

    @Column(name = "status", nullable = false, columnDefinition = "DEFAULT 'PENDING'")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "notes")
    private String notes;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "created_at")),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "updated_at")),
    })
    private EntityMetadata entityMetadata;

    public Payroll() {
    }

    public Payroll(
        Integer id,
        Company company,
        Employee employee,
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
        List<PayrollItem> items,
        PaymentStatus status,
        String notes,
        EntityMetadata entityMetadata
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
        this.irrfTaxRate = irrfTaxRate;
        this.fgtsBaseAmount = fgtsBaseAmount;
        this.fgtsPayed = fgtsPayed;
        this.totalAdditions = totalAdditions;
        this.totalDiscounts = totalDiscounts;
        this.totalLiquid = totalLiquid;
        this.items = items;
        this.status = status;
        this.notes = notes;
        this.entityMetadata = entityMetadata;
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

    public List<PayrollItem> getItems() {
        return items;
    }

    public void setItems(List<PayrollItem> items) {
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

    public EntityMetadata getEntityMetadata() {
        return entityMetadata;
    }

    public void setEntityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }
}