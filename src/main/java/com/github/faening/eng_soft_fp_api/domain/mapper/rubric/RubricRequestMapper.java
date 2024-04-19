package com.github.faening.eng_soft_fp_api.domain.mapper.rubric;

import com.github.faening.eng_soft_fp_api.data.model.Rubric;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class RubricRequestMapper extends AbstractMapper<Rubric, RubricRequestDTO> {
    @Autowired
    public RubricRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Rubric.class, RubricRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Rubric::getCode, RubricRequestDTO::setCode);
                mapper.when(notNull).map(Rubric::getName, RubricRequestDTO::setName);
                mapper.when(notNull).map(Rubric::getKind, RubricRequestDTO::setKind);
                mapper.when(notNull).map(Rubric::getDescription, RubricRequestDTO::setDescription);
                mapper.when(notNull).map(Rubric::getType, RubricRequestDTO::setType);
                mapper.when(notNull).map(Rubric::getEnabled, RubricRequestDTO::setEnabled);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(RubricRequestDTO.class, Rubric.class)
            .addMappings(mapper -> {
                mapper.skip(Rubric::setId);
                mapper.skip(Rubric::setEntityMetadata);
                mapper.when(notNull).map(RubricRequestDTO::getCode, Rubric::setCode);
                mapper.when(notNull).map(RubricRequestDTO::getName, Rubric::setName);
                mapper.when(notNull).map(RubricRequestDTO::getKind, Rubric::setKind);
                mapper.when(notNull).map(RubricRequestDTO::getDescription, Rubric::setDescription);
                mapper.when(notNull).map(RubricRequestDTO::getType, Rubric::setType);
                mapper.when(notNull).map(RubricRequestDTO::getEnabled, Rubric::setEnabled);
            });
    }
}