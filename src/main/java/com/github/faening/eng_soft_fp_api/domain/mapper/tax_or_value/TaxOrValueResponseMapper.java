package com.github.faening.eng_soft_fp_api.domain.mapper.tax_or_value;

import com.github.faening.eng_soft_fp_api.data.model.TaxOrValue;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class TaxOrValueResponseMapper extends AbstractMapper<TaxOrValue, TaxOrValueResponseDTO> {
    @Autowired
    public TaxOrValueResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(TaxOrValue.class, TaxOrValueResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(TaxOrValue::getId, TaxOrValueResponseDTO::setId);
                mapper.when(notNull).map(TaxOrValue::getType, TaxOrValueResponseDTO::setType);
                mapper.when(notNull).map(TaxOrValue::getRange, TaxOrValueResponseDTO::setRange);
                mapper.when(notNull).map(TaxOrValue::getRangeMinimumWage, TaxOrValueResponseDTO::setRangeMinimumWage);
                mapper.when(notNull).map(TaxOrValue::getRangeMaximumWage, TaxOrValueResponseDTO::setRangeMaximumWage);
                mapper.when(notNull).map(TaxOrValue::getFixedValue, TaxOrValueResponseDTO::setFixedValue);
                mapper.when(notNull).map(TaxOrValue::getTaxPercentage, TaxOrValueResponseDTO::setTaxPercentage);
                mapper.when(notNull).map(TaxOrValue::getDescription, TaxOrValueResponseDTO::setDescription);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), TaxOrValueResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), TaxOrValueResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(TaxOrValueResponseDTO.class, TaxOrValue.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(TaxOrValueResponseDTO::getId, TaxOrValue::setId);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getType, TaxOrValue::setType);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getRange, TaxOrValue::setRange);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getRangeMinimumWage, TaxOrValue::setRangeMinimumWage);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getRangeMaximumWage, TaxOrValue::setRangeMaximumWage);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getFixedValue, TaxOrValue::setFixedValue);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getTaxPercentage, TaxOrValue::setTaxPercentage);
                mapper.when(notNull).map(TaxOrValueResponseDTO::getDescription, TaxOrValue::setDescription);

                mapper.when(notNull).<LocalDateTime>map(TaxOrValueResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(TaxOrValueResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}