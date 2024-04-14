package com.github.faening.eng_soft_fp_api.domain.mapper.benefit_voucher;

import com.github.faening.eng_soft_fp_api.data.model.BenefitVoucher;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher.BenefitVoucherResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class BenefitVoucherResponseMapper extends AbstractMapper<BenefitVoucher, BenefitVoucherResponseDTO> {
    @Autowired
    public BenefitVoucherResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(BenefitVoucher.class, BenefitVoucherResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(BenefitVoucher::getId, BenefitVoucherResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), BenefitVoucherResponseDTO::setEmployeeId);
                mapper.when(notNull).map(BenefitVoucher::getPaidValue, BenefitVoucherResponseDTO::setPaidValue);
                mapper.when(notNull).map(BenefitVoucher::getReleaseDate, BenefitVoucherResponseDTO::setReleaseDate);
                mapper.when(notNull).map(BenefitVoucher::getDescription, BenefitVoucherResponseDTO::setDescription);
                mapper.when(notNull).map(BenefitVoucher::getBenefitType, BenefitVoucherResponseDTO::setBenefitType);
                mapper.when(notNull).map(BenefitVoucher::getPaymentStatus, BenefitVoucherResponseDTO::setPaymentStatus);
                mapper.when(notNull).map(BenefitVoucher::getPayrollDeductible, BenefitVoucherResponseDTO::setPayrollDeductible);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), BenefitVoucherResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), BenefitVoucherResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(BenefitVoucherResponseDTO.class, BenefitVoucher.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getId, BenefitVoucher::setId);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getEmployeeId, BenefitVoucher::setEmployee);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getPaidValue, BenefitVoucher::setPaidValue);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getReleaseDate, BenefitVoucher::setReleaseDate);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getDescription, BenefitVoucher::setDescription);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getBenefitType, BenefitVoucher::setBenefitType);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getPaymentStatus, BenefitVoucher::setPaymentStatus);
                mapper.when(notNull).map(BenefitVoucherResponseDTO::getPayrollDeductible, BenefitVoucher::setPayrollDeductible);
                mapper.when(notNull).<LocalDateTime>map(BenefitVoucherResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(BenefitVoucherResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}