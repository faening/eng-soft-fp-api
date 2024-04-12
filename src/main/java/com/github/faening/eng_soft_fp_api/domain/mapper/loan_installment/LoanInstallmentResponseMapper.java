package com.github.faening.eng_soft_fp_api.domain.mapper.loan_installment;

import com.github.faening.eng_soft_fp_api.data.model.LoanInstallment;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LoanInstallmentResponseMapper extends AbstractMapper<LoanInstallment, LoanInstallmentResponseDTO> {
    @Autowired
    public LoanInstallmentResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(LoanInstallment.class, LoanInstallmentResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LoanInstallment::getId, LoanInstallmentResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getLoan().getId(), LoanInstallmentResponseDTO::setLoanId);
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentNumber(), LoanInstallmentResponseDTO::setInstallmentNumber);
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentValue(), LoanInstallmentResponseDTO::setInstallmentValue);
                mapper.when(notNull).map(src -> src.getInstallment().getDiscountMonth(), LoanInstallmentResponseDTO::setDiscountMonth);
                mapper.when(notNull).map(src -> src.getInstallment().getPaymentStatus(), LoanInstallmentResponseDTO::setPaymentStatus);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), LoanInstallmentResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), LoanInstallmentResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(LoanInstallmentResponseDTO.class, LoanInstallment.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LoanInstallmentResponseDTO::getId, LoanInstallment::setId);
                mapper.when(notNull).map(LoanInstallmentResponseDTO::getLoanId, LoanInstallment::setLoan);
                mapper.when(notNull).<Integer>map(LoanInstallmentResponseDTO::getInstallmentNumber, (dest, v) -> dest.getInstallment().setInstallmentNumber(v));
                mapper.when(notNull).<BigDecimal>map(LoanInstallmentResponseDTO::getInstallmentValue, (dest, v) -> dest.getInstallment().setInstallmentValue(v));
                mapper.when(notNull).<Month>map(LoanInstallmentResponseDTO::getDiscountMonth, (dest, v) -> dest.getInstallment().setDiscountMonth(v));
                mapper.when(notNull).<PaymentStatus>map(LoanInstallmentResponseDTO::getPaymentStatus, (dest, v) -> dest.getInstallment().setPaymentStatus(v));
                mapper.when(notNull).<LocalDateTime>map(LoanInstallmentResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(LoanInstallmentResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}