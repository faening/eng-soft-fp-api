package com.github.faening.eng_soft_fp_api.domain.mapper.rubric;

import com.github.faening.eng_soft_fp_api.data.model.Rubric;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class RubricResponseMapper extends AbstractMapper<Rubric, RubricResponseDTO> {
    @Autowired
    public RubricResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Rubric.class, RubricResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Rubric::getId, RubricResponseDTO::setId);
                mapper.when(notNull).map(Rubric::getCode, RubricResponseDTO::setCode);
                mapper.when(notNull).map(Rubric::getName, RubricResponseDTO::setName);
                mapper.when(notNull).map(Rubric::getKind, RubricResponseDTO::setKind);
                mapper.when(notNull).map(Rubric::getDescription, RubricResponseDTO::setDescription);
                mapper.when(notNull).map(Rubric::getType, RubricResponseDTO::setType);
                mapper.when(notNull).map(Rubric::getEnabled, RubricResponseDTO::setEnabled);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), RubricResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), RubricResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(RubricResponseDTO.class, Rubric.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(RubricResponseDTO::getId, Rubric::setId);
                mapper.when(notNull).map(RubricResponseDTO::getCode, Rubric::setCode);
                mapper.when(notNull).map(RubricResponseDTO::getName, Rubric::setName);
                mapper.when(notNull).map(RubricResponseDTO::getKind, Rubric::setKind);
                mapper.when(notNull).map(RubricResponseDTO::getDescription, Rubric::setDescription);
                mapper.when(notNull).map(RubricResponseDTO::getType, Rubric::setType);
                mapper.when(notNull).map(RubricResponseDTO::getEnabled, Rubric::setEnabled);
                mapper.when(notNull).<LocalDateTime>map(RubricResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(RubricResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}