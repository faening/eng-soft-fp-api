package com.github.faening.eng_soft_fp_api.domain.mapper.absence_sheet;

import com.github.faening.eng_soft_fp_api.data.model.AbsenceSheet;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class AbsenceSheetRequestMapper extends AbstractMapper<AbsenceSheet, AbsenceSheetRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public AbsenceSheetRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(AbsenceSheet.class, AbsenceSheetRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getEmployee().getId(), AbsenceSheetRequestDTO::setType);
                mapper.when(notNull).map(AbsenceSheet::getType, AbsenceSheetRequestDTO::setType);
                mapper.when(notNull).map(AbsenceSheet::getStartDate, AbsenceSheetRequestDTO::setStartDate);
                mapper.when(notNull).map(AbsenceSheet::getEndDate, AbsenceSheetRequestDTO::setEndDate);
                mapper.when(notNull).map(AbsenceSheet::getObservation, AbsenceSheetRequestDTO::setObservation);
                mapper.when(notNull).map(AbsenceSheet::getStatus, AbsenceSheetRequestDTO::setStatus);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(AbsenceSheetRequestDTO.class, AbsenceSheet.class)
            .addMappings(mapper -> {
                mapper.skip(AbsenceSheet::setId);
                mapper.skip(AbsenceSheet::setEmployee);
                mapper.skip(AbsenceSheet::setEntityMetadata);

                mapper.when(notNull).using(employeeIdToEmployee).map(AbsenceSheetRequestDTO::getEmployeeId, AbsenceSheet::setEmployee);
                mapper.when(notNull).map(AbsenceSheetRequestDTO::getType, AbsenceSheet::setType);
                mapper.when(notNull).map(AbsenceSheetRequestDTO::getStartDate, AbsenceSheet::setStartDate);
                mapper.when(notNull).map(AbsenceSheetRequestDTO::getEndDate, AbsenceSheet::setEndDate);
                mapper.when(notNull).map(AbsenceSheetRequestDTO::getObservation, AbsenceSheet::setObservation);
                mapper.when(notNull).map(AbsenceSheetRequestDTO::getStatus, AbsenceSheet::setStatus);
            });
    }
}