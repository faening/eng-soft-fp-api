package com.github.faening.eng_soft_fp_api.domain.model.union_contribution;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class UnionContributionResponseDTO implements Serializable {
    private Integer id;
    private Integer employeeId;
    private Integer releaseYear;
    private Boolean optedOut;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UnionContributionResponseDTO() {
    }

    public UnionContributionResponseDTO(
        Integer id,
        Integer employeeId,
        Integer releaseYear,
        Boolean optedOut,
        PaymentStatus paymentStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.employeeId = employeeId;
        this.releaseYear = releaseYear;
        this.optedOut = optedOut;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean getOptedOut() {
        return optedOut;
    }

    public void setOptedOut(Boolean optedOut) {
        this.optedOut = optedOut;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
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

    @Override
    public String toString() {
        return "UnionContributionResponseDTO{" +
            "id=" + id +
            ", employeeId=" + employeeId +
            ", releaseYear=" + releaseYear +
            ", optedOut=" + optedOut +
            ", paymentStatus=" + paymentStatus +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}