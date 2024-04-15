package com.github.faening.eng_soft_fp_api.domain.mapper.work_shift;

import com.github.faening.eng_soft_fp_api.data.model.WorkShift;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class WorkShiftRequestMapper extends AbstractMapper<WorkShift, WorkShiftRequestDTO> {
    public WorkShiftRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(WorkShift.class, WorkShiftRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(WorkShift::getDescription, WorkShiftRequestDTO::setDescription);
                mapper.when(notNull).map(WorkShift::getStartOfWorkday, WorkShiftRequestDTO::setStartOfWorkday);
                mapper.when(notNull).map(WorkShift::getStartOfBreak, WorkShiftRequestDTO::setStartOfBreak);
                mapper.when(notNull).map(WorkShift::getEndOfBreak, WorkShiftRequestDTO::setEndOfBreak);
                mapper.when(notNull).map(WorkShift::getEndOfWorkday, WorkShiftRequestDTO::setEndOfWorkday);
                mapper.when(notNull).map(WorkShift::getNightShiftAllowance, WorkShiftRequestDTO::setNightShiftAllowance);
                mapper.when(notNull).map(WorkShift::getEnabled, WorkShiftRequestDTO::setEnabled);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(WorkShiftRequestDTO.class, WorkShift.class)
            .addMappings(mapper -> {
                mapper.skip(WorkShift::setId);
                mapper.skip(WorkShift::setEntityMetadata);
                mapper.when(notNull).map(WorkShiftRequestDTO::getDescription, WorkShift::setDescription);
                mapper.when(notNull).map(WorkShiftRequestDTO::getStartOfWorkday, WorkShift::setStartOfWorkday);
                mapper.when(notNull).map(WorkShiftRequestDTO::getStartOfBreak, WorkShift::setStartOfBreak);
                mapper.when(notNull).map(WorkShiftRequestDTO::getEndOfBreak, WorkShift::setEndOfBreak);
                mapper.when(notNull).map(WorkShiftRequestDTO::getEndOfWorkday, WorkShift::setEndOfWorkday);
                mapper.when(notNull).map(WorkShiftRequestDTO::getNightShiftAllowance, WorkShift::setNightShiftAllowance);
                mapper.when(notNull).map(WorkShiftRequestDTO::getEnabled, WorkShift::setEnabled);
            });

    }
}
