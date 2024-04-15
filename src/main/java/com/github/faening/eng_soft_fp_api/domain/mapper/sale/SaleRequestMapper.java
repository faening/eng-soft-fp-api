package com.github.faening.eng_soft_fp_api.domain.mapper.sale;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Sale;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.sale.SaleRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class SaleRequestMapper extends AbstractMapper<Sale, SaleRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public SaleRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Sale.class, SaleRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getEmployee().getId(), SaleRequestDTO::setEmployeeId);
                mapper.when(notNull).map(Sale::getDate, SaleRequestDTO::setDate);
                mapper.when(notNull).map(Sale::getAmount, SaleRequestDTO::setAmount);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(SaleRequestDTO.class, Sale.class)
            .addMappings(mapper -> {
                mapper.skip(Sale::setId);
                mapper.skip(Sale::setEntityMetadata);
                mapper.when(notNull).map(SaleRequestDTO::getDate, Sale::setDate);
                mapper.when(notNull).using(employeeIdToEmployee).map(SaleRequestDTO::getEmployeeId, Sale::setEmployee);
                mapper.when(notNull).map(SaleRequestDTO::getAmount, Sale::setAmount);
            });
    }
}