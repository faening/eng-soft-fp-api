package com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge;

import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge.LegalChargeResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LegalChargeResponseMapper extends AbstractMapper<LegalCharge, LegalChargeResponseDTO> {
    @Autowired
    public LegalChargeResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(LegalCharge.class, LegalChargeResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LegalCharge::getId, LegalChargeResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), LegalChargeResponseDTO::setEmployeeId);
                mapper.when(notNull).map(LegalCharge::getLegalChargeAmount, LegalChargeResponseDTO::setLegalChargeAmount);
                mapper.when(notNull).map(LegalCharge::getPercentage, LegalChargeResponseDTO::setPercentage);
                mapper.when(notNull).map(LegalCharge::getReleaseDate, LegalChargeResponseDTO::setReleaseDate);
                mapper.when(notNull).map(LegalCharge::getRecurring, LegalChargeResponseDTO::setRecurring);
                mapper.when(notNull).map(LegalCharge::getInstallments, LegalChargeResponseDTO::setInstallments);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), LegalChargeResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), LegalChargeResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(LegalChargeResponseDTO.class, LegalCharge.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LegalChargeResponseDTO::getId, LegalCharge::setId);
                mapper.when(notNull).<Integer>map(LegalChargeResponseDTO::getEmployeeId, (dest, v) -> dest.getEmployee().setId(v));
                mapper.when(notNull).map(LegalChargeResponseDTO::getLegalChargeAmount, LegalCharge::setLegalChargeAmount);
                mapper.when(notNull).map(LegalChargeResponseDTO::getPercentage, LegalCharge::setPercentage);
                mapper.when(notNull).map(LegalChargeResponseDTO::getReleaseDate, LegalCharge::setReleaseDate);
                mapper.when(notNull).map(LegalChargeResponseDTO::getRecurring, LegalCharge::setRecurring);
                mapper.when(notNull).map(LegalChargeResponseDTO::getInstallments, LegalCharge::setInstallments);
                mapper.when(notNull).<LocalDateTime>map(LegalChargeResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(LegalChargeResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}