package com.github.faening.eng_soft_fp_api.domain.model.trip_expense;

import com.github.faening.eng_soft_fp_api.domain.enumeration.ApprovalStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class TripExpenseRequestDTO implements Serializable {
    private Integer employeeId;
    private LocalDate date;
    private BigDecimal amount;
    private ApprovalStatus status;

    public TripExpenseRequestDTO() {
    }

    public TripExpenseRequestDTO(
        Integer employeeId,
        LocalDate date,
        BigDecimal amount,
        ApprovalStatus status
    ) {
        this.employeeId = employeeId;
        this.date = date;
        this.amount = amount;
        this.status = status;
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

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TripExpenseRequestDTO{" +
            ", employeeId=" + employeeId +
            ", date=" + date +
            ", amount=" + amount +
            ", status=" + status +
            '}';
    }
}