package com.github.faening.eng_soft_fp_api.domain.mapper.hours_worked_sheet;

import com.github.faening.eng_soft_fp_api.data.model.HoursWorkedSheet;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class HoursWorkedSheetRequestMapper extends AbstractMapper<HoursWorkedSheet, HoursWorkedSheetRequestDTO> {
    @Autowired
    public HoursWorkedSheetRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(HoursWorkedSheet.class, HoursWorkedSheetRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(HoursWorkedSheet::getDate, HoursWorkedSheetRequestDTO::setDate);
                mapper.when(notNull).map(HoursWorkedSheet::getRegularHours, HoursWorkedSheetRequestDTO::setRegularHours);
                mapper.when(notNull).map(HoursWorkedSheet::getNegativeHours, HoursWorkedSheetRequestDTO::setNegativeHours);
                mapper.when(notNull).map(HoursWorkedSheet::getOvertime50, HoursWorkedSheetRequestDTO::setOvertime50);
                mapper.when(notNull).map(HoursWorkedSheet::getOvertime100, HoursWorkedSheetRequestDTO::setOvertime100);
                mapper.when(notNull).map(HoursWorkedSheet::getTimeBank, HoursWorkedSheetRequestDTO::setTimeBank);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(HoursWorkedSheetRequestDTO.class, HoursWorkedSheet.class)
            .addMappings(mapper -> {
                mapper.skip(HoursWorkedSheet::setId);
                mapper.skip(HoursWorkedSheet::setEmployee);
                mapper.skip(HoursWorkedSheet::setEntityMetadata);

                mapper.when(notNull).map(HoursWorkedSheetRequestDTO::getDate, HoursWorkedSheet::setDate);
                mapper.when(notNull).map(HoursWorkedSheetRequestDTO::getRegularHours, HoursWorkedSheet::setRegularHours);
                mapper.when(notNull).map(HoursWorkedSheetRequestDTO::getNegativeHours, HoursWorkedSheet::setNegativeHours);
                mapper.when(notNull).map(HoursWorkedSheetRequestDTO::getOvertime50, HoursWorkedSheet::setOvertime50);
                mapper.when(notNull).map(HoursWorkedSheetRequestDTO::getOvertime100, HoursWorkedSheet::setOvertime100);
                mapper.when(notNull).map(HoursWorkedSheetRequestDTO::getTimeBank, HoursWorkedSheet::setTimeBank);
            });
    }
}