package com.github.faening.eng_soft_fp_api.domain.mapper.sale;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Sale;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Service
public class SaleResponseMapper extends AbstractMapper<Sale, SaleResponseDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public SaleResponseMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Sale.class, SaleResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Sale::getId, SaleResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), SaleResponseDTO::setEmployeeId);
                mapper.when(notNull).map(Sale::getDate, SaleResponseDTO::setDate);
                mapper.when(notNull).map(Sale::getAmount, SaleResponseDTO::setAmount);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), SaleResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), SaleResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEmployeeEntityById(context.getSource());

        modelMapper.createTypeMap(SaleResponseDTO.class, Sale.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(SaleResponseDTO::getId, Sale::setId);
                mapper.when(notNull).using(employeeIdToEmployee).map(SaleResponseDTO::getEmployeeId, Sale::setEmployee);
                mapper.when(notNull).map(SaleResponseDTO::getDate, Sale::setDate);
                mapper.when(notNull).map(SaleResponseDTO::getAmount, Sale::setAmount);

                mapper.when(notNull).<LocalDateTime>map(SaleResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(SaleResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}