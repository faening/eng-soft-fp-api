package com.github.faening.eng_soft_fp_api.domain.mapper.absence_sheet;

import com.github.faening.eng_soft_fp_api.data.model.AbsenceSheet;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.absence_sheet.AbsenceSheetResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class AbsenceSheetResponseMapper extends AbstractMapper<AbsenceSheet, AbsenceSheetResponseDTO> {
    @Autowired
    public AbsenceSheetResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(AbsenceSheet.class, AbsenceSheetResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(AbsenceSheet::getId, AbsenceSheetResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), AbsenceSheetResponseDTO::setEmployeeId);
                mapper.when(notNull).map(AbsenceSheet::getType, AbsenceSheetResponseDTO::setType);
                mapper.when(notNull).map(AbsenceSheet::getStartDate, AbsenceSheetResponseDTO::setStartDate);
                mapper.when(notNull).map(AbsenceSheet::getEndDate, AbsenceSheetResponseDTO::setEndDate);
                mapper.when(notNull).map(AbsenceSheet::getObservation, AbsenceSheetResponseDTO::setObservation);
                mapper.when(notNull).map(AbsenceSheet::getStatus, AbsenceSheetResponseDTO::setStatus);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), AbsenceSheetResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), AbsenceSheetResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(AbsenceSheetResponseDTO.class, AbsenceSheet.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getId, AbsenceSheet::setId);
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getEmployeeId, AbsenceSheet::setEmployee);
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getType, AbsenceSheet::setType);
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getStartDate, AbsenceSheet::setStartDate);
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getEndDate, AbsenceSheet::setEndDate);
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getObservation, AbsenceSheet::setObservation);
                mapper.when(notNull).map(AbsenceSheetResponseDTO::getStatus, AbsenceSheet::setStatus);

                mapper.when(notNull).<LocalDateTime>map(AbsenceSheetResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(AbsenceSheetResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}