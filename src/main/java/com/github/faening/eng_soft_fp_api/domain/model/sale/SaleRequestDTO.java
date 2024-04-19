package com.github.faening.eng_soft_fp_api.domain.model.sale;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused")
public class SaleRequestDTO implements Serializable {
    private Integer employeeId;
    private LocalDate date;
    private BigDecimal amount;

    public SaleRequestDTO() {
    }

    public SaleRequestDTO(Integer employeeId, LocalDate date, BigDecimal amount) {
        this.employeeId = employeeId;
        this.date = date;
        this.amount = amount;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SaleRequestDTO{" +
            "employeeId=" + employeeId +
            ", date=" + date +
            ", amount=" + amount +
            '}';
    }
}