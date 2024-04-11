package com.github.faening.eng_soft_fp_api.domain.mapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public abstract class AbstractMapper<S, D> {
    protected final ModelMapper modelMapper;

    @SuppressWarnings("rawtypes")
    public Condition notNull = ctx -> ctx.getSource() != null;

    public AbstractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        createSourceToDestinationMapping();
        createDestinationToSourceMapping();
    }

    protected abstract void createSourceToDestinationMapping();

    protected abstract void createDestinationToSourceMapping();

    public D toDTO(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public List<D> toDTO(List<S> source, Class<D> destinationClass) {
        return List.of(modelMapper.map(source, destinationClass));
    }

    public S toEntity(D destination, Class<S> sourceClass) {
        return modelMapper.map(destination, sourceClass);
    }

    public List<S> toEntity(List<S> destination, Class<S> sourceClass) {
        return List.of(modelMapper.map(destination, sourceClass));
    }

    public void updateSourceFromDestination(S source, D destination) {
        modelMapper.map(destination, source);
    }
}