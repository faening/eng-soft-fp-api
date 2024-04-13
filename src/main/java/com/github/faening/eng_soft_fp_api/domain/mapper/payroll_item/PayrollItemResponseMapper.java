package com.github.faening.eng_soft_fp_api.domain.mapper.payroll_item;

import com.github.faening.eng_soft_fp_api.data.model.PayrollItem;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class PayrollItemResponseMapper extends AbstractMapper<PayrollItem, PayrollItemResponseDTO> {
    @Autowired
    public PayrollItemResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(PayrollItem.class, PayrollItemResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(PayrollItem::getId, PayrollItemResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getPayroll().getId(), PayrollItemResponseDTO::setPayrollId);
                mapper.when(notNull).map(PayrollItem::getRubric, PayrollItemResponseDTO::setRubric);
                mapper.when(notNull).map(PayrollItem::getTaxOrValue, PayrollItemResponseDTO::setTaxOrValue);
                mapper.when(notNull).map(PayrollItem::getBaseValue, PayrollItemResponseDTO::setBaseValue);
                mapper.when(notNull).map(PayrollItem::getCalculatedValue, PayrollItemResponseDTO::setCalculatedValue);
                mapper.when(notNull).map(PayrollItem::getCalculatedValue, PayrollItemResponseDTO::setCalculatedValue);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), PayrollItemResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), PayrollItemResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(PayrollItemResponseDTO.class, PayrollItem.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(PayrollItemResponseDTO::getId, PayrollItem::setId);
                mapper.when(notNull).map(PayrollItemResponseDTO::getPayrollId, PayrollItem::setPayroll);
                mapper.when(notNull).map(PayrollItemResponseDTO::getRubric, PayrollItem::setRubric);
                mapper.when(notNull).map(PayrollItemResponseDTO::getTaxOrValue, PayrollItem::setTaxOrValue);
                mapper.when(notNull).map(PayrollItemResponseDTO::getBaseValue, PayrollItem::setBaseValue);
                mapper.when(notNull).map(PayrollItemResponseDTO::getCalculatedValue, PayrollItem::setCalculatedValue);
                mapper.when(notNull).<LocalDateTime>map(PayrollItemResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(PayrollItemResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}