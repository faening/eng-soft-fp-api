package com.github.faening.eng_soft_fp_api.domain.mapper.trip_expense;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.TripExpense;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.trip_expense.TripExpenseResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class TripExpenseResponseMapper extends AbstractMapper<TripExpense, TripExpenseResponseDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public TripExpenseResponseMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(TripExpense.class, TripExpenseResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(TripExpense::getId, TripExpenseResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), TripExpenseResponseDTO::setEmployeeId);
                mapper.when(notNull).map(TripExpense::getDate, TripExpenseResponseDTO::setDate);
                mapper.when(notNull).map(TripExpense::getAmount, TripExpenseResponseDTO::setAmount);
                mapper.when(notNull).map(TripExpense::getStatus, TripExpenseResponseDTO::setStatus);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), TripExpenseResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), TripExpenseResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(TripExpenseResponseDTO.class, TripExpense.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(TripExpenseResponseDTO::getId, TripExpense::setId);
                mapper.when(notNull).using(employeeIdToEmployee).map(TripExpenseResponseDTO::getEmployeeId, TripExpense::setEmployee);
                mapper.when(notNull).map(TripExpenseResponseDTO::getDate, TripExpense::setDate);
                mapper.when(notNull).map(TripExpenseResponseDTO::getAmount, TripExpense::setAmount);
                mapper.when(notNull).map(TripExpenseResponseDTO::getStatus, TripExpense::setStatus);
                mapper.when(notNull).<LocalDateTime>map(TripExpenseResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(TripExpenseResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}
