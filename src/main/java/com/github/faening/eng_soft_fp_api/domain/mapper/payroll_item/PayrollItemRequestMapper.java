package com.github.faening.eng_soft_fp_api.domain.mapper.payroll_item;

import com.github.faening.eng_soft_fp_api.data.model.PayrollItem;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unused")
public class PayrollItemRequestMapper extends AbstractMapper<PayrollItem, PayrollItemRequestDTO> {
    @Autowired
    public PayrollItemRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(PayrollItem.class, PayrollItemRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(PayrollItem::getRubric, PayrollItemRequestDTO::setRubric);
                mapper.when(notNull).map(PayrollItem::getTaxOrValue, PayrollItemRequestDTO::setTaxOrValue);
                mapper.when(notNull).map(PayrollItem::getBaseValue, PayrollItemRequestDTO::setBaseValue);
                mapper.when(notNull).map(PayrollItem::getCalculatedValue, PayrollItemRequestDTO::setCalculatedValue);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(PayrollItemRequestDTO.class, PayrollItem.class)
            .addMappings(mapper -> {
                mapper.skip(PayrollItem::setId);
                mapper.skip(PayrollItem::setPayroll);
                mapper.skip(PayrollItem::setEntityMetadata);
                mapper.when(notNull).map(PayrollItemRequestDTO::getRubric, PayrollItem::setRubric);
                mapper.when(notNull).map(PayrollItemRequestDTO::getTaxOrValue, PayrollItem::setTaxOrValue);
                mapper.when(notNull).map(PayrollItemRequestDTO::getBaseValue, PayrollItem::setBaseValue);
                mapper.when(notNull).map(PayrollItemRequestDTO::getCalculatedValue, PayrollItem::setCalculatedValue);
            });
    }
}