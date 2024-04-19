package com.github.faening.eng_soft_fp_api.domain.model.union_contribution;

import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;

import java.io.Serializable;

@SuppressWarnings("unused")
public class UnionContributionRequestDTO implements Serializable {
    private Integer employeeId;
    private Integer releaseYear;
    private Boolean optedOut;
    private PaymentStatus paymentStatus;

    public UnionContributionRequestDTO() {
    }

    public UnionContributionRequestDTO(Integer employeeId, Integer releaseYear, Boolean optedOut, PaymentStatus paymentStatus) {
        this.employeeId = employeeId;
        this.releaseYear = releaseYear;
        this.optedOut = optedOut;
        this.paymentStatus = paymentStatus;
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

    @Override
    public String toString() {
        return "UnionContributionRequestDTO{" +
            "employeeId=" + employeeId +
            ", releaseYear=" + releaseYear +
            ", optedOut=" + optedOut +
            ", paymentStatus=" + paymentStatus +
            '}';
    }
}