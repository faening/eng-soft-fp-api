package com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge_installment;

import com.github.faening.eng_soft_fp_api.data.model.LegalChargeInstallment;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment.LegalChargeInstallmentRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LegalChargeInstallmentRequestMapper extends AbstractMapper<LegalChargeInstallment, LegalChargeInstallmentRequestDTO> {
    @Autowired
    public LegalChargeInstallmentRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(LegalChargeInstallment.class, LegalChargeInstallmentRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentNumber(), LegalChargeInstallmentRequestDTO::setInstallmentNumber);
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentValue(), LegalChargeInstallmentRequestDTO::setInstallmentValue);
                mapper.when(notNull).map(src -> src.getInstallment().getDiscountMonth(), LegalChargeInstallmentRequestDTO::setDiscountMonth);
                mapper.when(notNull).map(src -> src.getInstallment().getPaymentStatus(), LegalChargeInstallmentRequestDTO::setPaymentStatus);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(LegalChargeInstallmentRequestDTO.class, LegalChargeInstallment.class)
            .addMappings(mapper -> {
                mapper.skip(LegalChargeInstallment::setId);
                mapper.skip(LegalChargeInstallment::setLegalCharge);
                mapper.skip(LegalChargeInstallment::setEntityMetadata);
                mapper.when(notNull).<Integer>map(LegalChargeInstallmentRequestDTO::getInstallmentNumber, (dest, v) -> dest.getInstallment().setInstallmentNumber(v));
                mapper.when(notNull).<BigDecimal>map(LegalChargeInstallmentRequestDTO::getInstallmentValue, (dest, v) -> dest.getInstallment().setInstallmentValue(v));
                mapper.when(notNull).<Month>map(LegalChargeInstallmentRequestDTO::getDiscountMonth, (dest, v) -> dest.getInstallment().setDiscountMonth(v));
                mapper.when(notNull).<PaymentStatus>map(LegalChargeInstallmentRequestDTO::getPaymentStatus, (dest, v) -> dest.getInstallment().setPaymentStatus(v));
            });
    }
}