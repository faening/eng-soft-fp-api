package com.github.faening.eng_soft_fp_api.domain.mapper.work_shift;

import com.github.faening.eng_soft_fp_api.data.model.WorkShift;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.work_shift.WorkShiftDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class WorkShiftMapper extends AbstractMapper<WorkShift, WorkShiftDTO> {
    public WorkShiftMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    public WorkShiftDTO mapWorkShiftToWorkShiftDTO(WorkShift workShift) {
        return modelMapper.map(workShift, WorkShiftDTO.class);
    }

    public List<WorkShiftDTO> mapWorkShiftToWorkShiftDTO(List<WorkShift> workShift) {
        return List.of(modelMapper.map(workShift, WorkShiftDTO[].class));
    }

    public WorkShift mapWorkShiftDTOToWorkShift(WorkShiftDTO workShiftDTO) {
        return modelMapper.map(workShiftDTO, WorkShift.class);
    }

    public List<WorkShift> mapWorkShiftDTOToWorkShift(List<WorkShiftDTO> workShiftDTOs) {
        return List.of(modelMapper.map(workShiftDTOs, WorkShift[].class));
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(WorkShift.class, WorkShiftDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(WorkShift::getId, WorkShiftDTO::setId);
                mapper.when(notNull).map(WorkShift::getDescription, WorkShiftDTO::setDescription);
                mapper.when(notNull).map(WorkShift::getStartOfWorkday, WorkShiftDTO::setStartOfWorkday);
                mapper.when(notNull).map(WorkShift::getStartOfBreak, WorkShiftDTO::setStartOfBreak);
                mapper.when(notNull).map(WorkShift::getEndOfBreak, WorkShiftDTO::setEndOfBreak);
                mapper.when(notNull).map(WorkShift::getEndOfWorkday, WorkShiftDTO::setEndOfWorkday);
                mapper.when(notNull).map(WorkShift::getNightShiftAllowance, WorkShiftDTO::setNightShiftAllowance);
                mapper.when(notNull).map(WorkShift::getEnabled, WorkShiftDTO::setEnabled);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), WorkShiftDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), WorkShiftDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(WorkShiftDTO.class, WorkShift.class)
            .addMappings(mapper -> {
                mapper.skip(WorkShift::setId);
                mapper.skip(WorkShift::setEntityMetadata);

                mapper.when(notNull).map(WorkShiftDTO::getDescription, WorkShift::setDescription);
                mapper.when(notNull).map(WorkShiftDTO::getStartOfWorkday, WorkShift::setStartOfWorkday);
                mapper.when(notNull).map(WorkShiftDTO::getStartOfBreak, WorkShift::setStartOfBreak);
                mapper.when(notNull).map(WorkShiftDTO::getEndOfBreak, WorkShift::setEndOfBreak);
                mapper.when(notNull).map(WorkShiftDTO::getEndOfWorkday, WorkShift::setEndOfWorkday);
                mapper.when(notNull).map(WorkShiftDTO::getNightShiftAllowance, WorkShift::setNightShiftAllowance);
                mapper.when(notNull).map(WorkShiftDTO::getEnabled, WorkShift::setEnabled);
            });
    }
}
