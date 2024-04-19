package com.github.faening.eng_soft_fp_api.domain.mapper.loan_installment;

import com.github.faening.eng_soft_fp_api.data.model.LoanInstallment;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
@Service
public class LoanInstallmentRequestMapper extends AbstractMapper<LoanInstallment, LoanInstallmentRequestDTO> {
    @Autowired
    public LoanInstallmentRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(LoanInstallment.class, LoanInstallmentRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentNumber(), LoanInstallmentRequestDTO::setInstallmentNumber);
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentValue(), LoanInstallmentRequestDTO::setInstallmentValue);
                mapper.when(notNull).map(src -> src.getInstallment().getDiscountMonth(), LoanInstallmentRequestDTO::setDiscountMonth);
                mapper.when(notNull).map(src -> src.getInstallment().getPaymentStatus(), LoanInstallmentRequestDTO::setPaymentStatus);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(LoanInstallmentRequestDTO.class, LoanInstallment.class)
            .addMappings(mapper -> {
                mapper.skip(LoanInstallment::setId);
                mapper.skip(LoanInstallment::setLoan);
                mapper.skip(LoanInstallment::setEntityMetadata);
                mapper.when(notNull).<Integer>map(LoanInstallmentRequestDTO::getInstallmentNumber, (dest, v) -> dest.getInstallment().setInstallmentNumber(v));
                mapper.when(notNull).<BigDecimal>map(LoanInstallmentRequestDTO::getInstallmentValue, (dest, v) -> dest.getInstallment().setInstallmentValue(v));
                mapper.when(notNull).<Month>map(LoanInstallmentRequestDTO::getDiscountMonth, (dest, v) -> dest.getInstallment().setDiscountMonth(v));
                mapper.when(notNull).<PaymentStatus>map(LoanInstallmentRequestDTO::getPaymentStatus, (dest, v) -> dest.getInstallment().setPaymentStatus(v));
            });
    }
}