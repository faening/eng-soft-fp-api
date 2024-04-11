package com.github.faening.eng_soft_fp_api.domain.mapper.work_shift;

import com.github.faening.eng_soft_fp_api.data.model.WorkShift;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class WorkShiftResponseMapper extends AbstractMapper<WorkShift, WorkShiftResponseDTO> {
    public WorkShiftResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(WorkShift.class, WorkShiftResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(WorkShift::getId, WorkShiftResponseDTO::setId);
                mapper.when(notNull).map(WorkShift::getDescription, WorkShiftResponseDTO::setDescription);
                mapper.when(notNull).map(WorkShift::getStartOfWorkday, WorkShiftResponseDTO::setStartOfWorkday);
                mapper.when(notNull).map(WorkShift::getStartOfBreak, WorkShiftResponseDTO::setStartOfBreak);
                mapper.when(notNull).map(WorkShift::getEndOfBreak, WorkShiftResponseDTO::setEndOfBreak);
                mapper.when(notNull).map(WorkShift::getEndOfWorkday, WorkShiftResponseDTO::setEndOfWorkday);
                mapper.when(notNull).map(WorkShift::getNightShiftAllowance, WorkShiftResponseDTO::setNightShiftAllowance);
                mapper.when(notNull).map(WorkShift::getEnabled, WorkShiftResponseDTO::setEnabled);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), WorkShiftResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), WorkShiftResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(WorkShiftResponseDTO.class, WorkShift.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(WorkShiftResponseDTO::getId, WorkShift::setId);
                mapper.when(notNull).map(WorkShiftResponseDTO::getDescription, WorkShift::setDescription);
                mapper.when(notNull).map(WorkShiftResponseDTO::getStartOfWorkday, WorkShift::setStartOfWorkday);
                mapper.when(notNull).map(WorkShiftResponseDTO::getStartOfBreak, WorkShift::setStartOfBreak);
                mapper.when(notNull).map(WorkShiftResponseDTO::getEndOfBreak, WorkShift::setEndOfBreak);
                mapper.when(notNull).map(WorkShiftResponseDTO::getEndOfWorkday, WorkShift::setEndOfWorkday);
                mapper.when(notNull).map(WorkShiftResponseDTO::getNightShiftAllowance, WorkShift::setNightShiftAllowance);
                mapper.when(notNull).map(WorkShiftResponseDTO::getEnabled, WorkShift::setEnabled);

                mapper.when(notNull).<LocalDateTime>map(WorkShiftResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(WorkShiftResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}
