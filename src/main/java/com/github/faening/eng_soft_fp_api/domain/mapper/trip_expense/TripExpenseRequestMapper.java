package com.github.faening.eng_soft_fp_api.domain.mapper.trip_expense;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.TripExpense;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.trip_expense.TripExpenseRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class TripExpenseRequestMapper extends AbstractMapper<TripExpense, TripExpenseRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public TripExpenseRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(TripExpense.class, TripExpenseRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getEmployee().getId(), TripExpenseRequestDTO::setEmployeeId);
                mapper.when(notNull).map(TripExpense::getDate, TripExpenseRequestDTO::setDate);
                mapper.when(notNull).map(TripExpense::getAmount, TripExpenseRequestDTO::setAmount);
                mapper.when(notNull).map(TripExpense::getStatus, TripExpenseRequestDTO::setStatus);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(TripExpenseRequestDTO.class, TripExpense.class)
            .addMappings(mapper -> {
                mapper.skip(TripExpense::setId);
                mapper.skip(TripExpense::setEntityMetadata);
                mapper.when(notNull).using(employeeIdToEmployee).map(TripExpenseRequestDTO::getEmployeeId, TripExpense::setEmployee);
                mapper.when(notNull).map(TripExpenseRequestDTO::getDate, TripExpense::setDate);
                mapper.when(notNull).map(TripExpenseRequestDTO::getAmount, TripExpense::setAmount);
                mapper.when(notNull).map(TripExpenseRequestDTO::getStatus, TripExpense::setStatus);
            });
    }
}
