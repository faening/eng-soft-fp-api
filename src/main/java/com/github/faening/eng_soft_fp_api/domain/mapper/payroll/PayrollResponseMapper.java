package com.github.faening.eng_soft_fp_api.domain.mapper.payroll;

import com.github.faening.eng_soft_fp_api.data.model.Payroll;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class PayrollResponseMapper extends AbstractMapper<Payroll, PayrollResponseDTO> {
    @Autowired
    public PayrollResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Payroll.class, PayrollResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Payroll::getId, PayrollResponseDTO::setId);
                mapper.when(notNull).map(Payroll::getCompany, PayrollResponseDTO::setCompany);
                mapper.when(notNull).map(Payroll::getEmployee, PayrollResponseDTO::setEmployee);
                mapper.when(notNull).map(Payroll::getMonth, PayrollResponseDTO::setMonth);
                mapper.when(notNull).map(Payroll::getYear, PayrollResponseDTO::setYear);
                mapper.when(notNull).map(Payroll::getGrossSalary, PayrollResponseDTO::setGrossSalary);
                mapper.when(notNull).map(Payroll::getInssBaseAmount, PayrollResponseDTO::setInssBaseAmount);
                mapper.when(notNull).map(Payroll::getInssTaxRate, PayrollResponseDTO::setInssTaxRate);
                mapper.when(notNull).map(Payroll::getIrrfBaseAmount, PayrollResponseDTO::setIrrfBaseAmount);
                mapper.when(notNull).map(Payroll::getIrrfTaxRate, PayrollResponseDTO::setIrrfTaxRate);
                mapper.when(notNull).map(Payroll::getFgtsPayed, PayrollResponseDTO::setFgtsPayed);
                mapper.when(notNull).map(Payroll::getTotalAdditions, PayrollResponseDTO::setTotalAdditions);
                mapper.when(notNull).map(Payroll::getTotalDiscounts, PayrollResponseDTO::setTotalDiscounts);
                mapper.when(notNull).map(Payroll::getTotalLiquid, PayrollResponseDTO::setTotalLiquid);
                mapper.when(notNull).map(Payroll::getItems, PayrollResponseDTO::setItems);
                mapper.when(notNull).map(Payroll::getStatus, PayrollResponseDTO::setStatus);
                mapper.when(notNull).map(Payroll::getNotes, PayrollResponseDTO::setNotes);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), PayrollResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), PayrollResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(PayrollResponseDTO.class, Payroll.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(PayrollResponseDTO::getId, Payroll::setId);
                mapper.when(notNull).map(PayrollResponseDTO::getCompany, Payroll::setCompany);
                mapper.when(notNull).map(PayrollResponseDTO::getEmployee, Payroll::setEmployee);
                mapper.when(notNull).map(PayrollResponseDTO::getMonth, Payroll::setMonth);
                mapper.when(notNull).map(PayrollResponseDTO::getYear, Payroll::setYear);
                mapper.when(notNull).map(PayrollResponseDTO::getGrossSalary, Payroll::setGrossSalary);
                mapper.when(notNull).map(PayrollResponseDTO::getInssBaseAmount, Payroll::setInssBaseAmount);
                mapper.when(notNull).map(PayrollResponseDTO::getInssTaxRate, Payroll::setInssTaxRate);
                mapper.when(notNull).map(PayrollResponseDTO::getIrrfBaseAmount, Payroll::setIrrfBaseAmount);
                mapper.when(notNull).map(PayrollResponseDTO::getIrrfTaxRate, Payroll::setIrrfTaxRate);
                mapper.when(notNull).map(PayrollResponseDTO::getFgtsPayed, Payroll::setFgtsPayed);
                mapper.when(notNull).map(PayrollResponseDTO::getTotalAdditions, Payroll::setTotalAdditions);
                mapper.when(notNull).map(PayrollResponseDTO::getTotalDiscounts, Payroll::setTotalDiscounts);
                mapper.when(notNull).map(PayrollResponseDTO::getTotalLiquid, Payroll::setTotalLiquid);
                mapper.when(notNull).map(PayrollResponseDTO::getItems, Payroll::setItems);
                mapper.when(notNull).map(PayrollResponseDTO::getStatus, Payroll::setStatus);
                mapper.when(notNull).map(PayrollResponseDTO::getNotes, Payroll::setNotes);
                mapper.when(notNull).<LocalDateTime>map(PayrollResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(PayrollResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}
