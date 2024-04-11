package com.github.faening.eng_soft_fp_api.domain.mapper.hours_worked_sheet;

import com.github.faening.eng_soft_fp_api.data.model.HoursWorkedSheet;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.hours_worked_sheet.HoursWorkedSheetResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Service
public class HoursWorkedSheetResponseMapper extends AbstractMapper<HoursWorkedSheet, HoursWorkedSheetResponseDTO> {
    @Autowired
    public HoursWorkedSheetResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(HoursWorkedSheet.class, HoursWorkedSheetResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(HoursWorkedSheet::getId, HoursWorkedSheetResponseDTO::setId);
                mapper.when(notNull).map(HoursWorkedSheet::getEmployee, HoursWorkedSheetResponseDTO::setEmployee);
                mapper.when(notNull).map(HoursWorkedSheet::getDate, HoursWorkedSheetResponseDTO::setDate);
                mapper.when(notNull).map(HoursWorkedSheet::getRegularHours, HoursWorkedSheetResponseDTO::setRegularHours);
                mapper.when(notNull).map(HoursWorkedSheet::getNegativeHours, HoursWorkedSheetResponseDTO::setNegativeHours);
                mapper.when(notNull).map(HoursWorkedSheet::getOvertime50, HoursWorkedSheetResponseDTO::setOvertime50);
                mapper.when(notNull).map(HoursWorkedSheet::getOvertime100, HoursWorkedSheetResponseDTO::setOvertime100);
                mapper.when(notNull).map(HoursWorkedSheet::getTimeBank, HoursWorkedSheetResponseDTO::setTimeBank);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), HoursWorkedSheetResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), HoursWorkedSheetResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(HoursWorkedSheetResponseDTO.class, HoursWorkedSheet.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getId, HoursWorkedSheet::setId);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getEmployee, HoursWorkedSheet::setEmployee);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getDate, HoursWorkedSheet::setDate);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getRegularHours, HoursWorkedSheet::setRegularHours);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getNegativeHours, HoursWorkedSheet::setNegativeHours);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getOvertime50, HoursWorkedSheet::setOvertime50);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getOvertime100, HoursWorkedSheet::setOvertime100);
                mapper.when(notNull).map(HoursWorkedSheetResponseDTO::getTimeBank, HoursWorkedSheet::setTimeBank);

                mapper.when(notNull).<LocalDateTime>map(HoursWorkedSheetResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(HoursWorkedSheetResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}
