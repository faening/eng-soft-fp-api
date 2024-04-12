package com.github.faening.eng_soft_fp_api.domain.mapper.tax_or_value;

import com.github.faening.eng_soft_fp_api.data.model.TaxOrValue;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class TaxOrValueRequestMapper extends AbstractMapper<TaxOrValue, TaxOrValueRequestDTO> {
    @Autowired
    public TaxOrValueRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(TaxOrValue.class, TaxOrValueRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(TaxOrValue::getType, TaxOrValueRequestDTO::setType);
                mapper.when(notNull).map(TaxOrValue::getRange, TaxOrValueRequestDTO::setRange);
                mapper.when(notNull).map(TaxOrValue::getRangeMinimumWage, TaxOrValueRequestDTO::setRangeMinimumWage);
                mapper.when(notNull).map(TaxOrValue::getRangeMaximumWage, TaxOrValueRequestDTO::setRangeMaximumWage);
                mapper.when(notNull).map(TaxOrValue::getFixedValue, TaxOrValueRequestDTO::setFixedValue);
                mapper.when(notNull).map(TaxOrValue::getTaxPercentage, TaxOrValueRequestDTO::setTaxPercentage);
                mapper.when(notNull).map(TaxOrValue::getDescription, TaxOrValueRequestDTO::setDescription);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(TaxOrValueRequestDTO.class, TaxOrValue.class)
            .addMappings(mapper -> {
                mapper.skip(TaxOrValue::setId);
                mapper.skip(TaxOrValue::setEntityMetadata);

                mapper.when(notNull).map(TaxOrValueRequestDTO::getType, TaxOrValue::setType);
                mapper.when(notNull).map(TaxOrValueRequestDTO::getRange, TaxOrValue::setRange);
                mapper.when(notNull).map(TaxOrValueRequestDTO::getRangeMinimumWage, TaxOrValue::setRangeMinimumWage);
                mapper.when(notNull).map(TaxOrValueRequestDTO::getRangeMaximumWage, TaxOrValue::setRangeMaximumWage);
                mapper.when(notNull).map(TaxOrValueRequestDTO::getFixedValue, TaxOrValue::setFixedValue);
                mapper.when(notNull).map(TaxOrValueRequestDTO::getTaxPercentage, TaxOrValue::setTaxPercentage);
                mapper.when(notNull).map(TaxOrValueRequestDTO::getDescription, TaxOrValue::setDescription);
            });
    }
}
