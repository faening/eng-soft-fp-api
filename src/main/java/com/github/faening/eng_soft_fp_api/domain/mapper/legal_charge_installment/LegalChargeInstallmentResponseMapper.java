package com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge_installment;

import com.github.faening.eng_soft_fp_api.data.model.LegalChargeInstallment;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Month;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment.LegalChargeInstallmentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LegalChargeInstallmentResponseMapper extends AbstractMapper<LegalChargeInstallment, LegalChargeInstallmentResponseDTO> {
    @Autowired
    public LegalChargeInstallmentResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(LegalChargeInstallment.class, LegalChargeInstallmentResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LegalChargeInstallment::getId, LegalChargeInstallmentResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getLegalCharge().getId(), LegalChargeInstallmentResponseDTO::setLegalChargeId);
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentNumber(), LegalChargeInstallmentResponseDTO::setInstallmentNumber);
                mapper.when(notNull).map(src -> src.getInstallment().getInstallmentValue(), LegalChargeInstallmentResponseDTO::setInstallmentValue);
                mapper.when(notNull).map(src -> src.getInstallment().getDiscountMonth(), LegalChargeInstallmentResponseDTO::setDiscountMonth);
                mapper.when(notNull).map(src -> src.getInstallment().getPaymentStatus(), LegalChargeInstallmentResponseDTO::setPaymentStatus);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), LegalChargeInstallmentResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), LegalChargeInstallmentResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(LegalChargeInstallmentResponseDTO.class, LegalChargeInstallment.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LegalChargeInstallmentResponseDTO::getId, LegalChargeInstallment::setId);
                mapper.when(notNull).map(LegalChargeInstallmentResponseDTO::getLegalChargeId, LegalChargeInstallment::setLegalCharge);
                mapper.when(notNull).<Integer>map(LegalChargeInstallmentResponseDTO::getInstallmentNumber, (dest, v) -> dest.getInstallment().setInstallmentNumber(v));
                mapper.when(notNull).<BigDecimal>map(LegalChargeInstallmentResponseDTO::getInstallmentValue, (dest, v) -> dest.getInstallment().setInstallmentValue(v));
                mapper.when(notNull).<Month>map(LegalChargeInstallmentResponseDTO::getDiscountMonth, (dest, v) -> dest.getInstallment().setDiscountMonth(v));
                mapper.when(notNull).<PaymentStatus>map(LegalChargeInstallmentResponseDTO::getPaymentStatus, (dest, v) -> dest.getInstallment().setPaymentStatus(v));
                mapper.when(notNull).<LocalDateTime>map(LegalChargeInstallmentResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(LegalChargeInstallmentResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}