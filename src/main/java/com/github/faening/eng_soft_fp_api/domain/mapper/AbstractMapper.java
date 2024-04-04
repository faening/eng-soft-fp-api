package com.github.faening.eng_soft_fp_api.domain.mapper;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public abstract class AbstractMapper<S, D> {
    protected final ModelMapper modelMapper;

    @SuppressWarnings("rawtypes")
    public Condition notNull = ctx -> ctx.getSource() != null;

    @Autowired
    public AbstractMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        createSourceToDestinationMapping();
        createDestinationToSourceMapping();
    }

    protected abstract void createSourceToDestinationMapping();

    protected abstract void createDestinationToSourceMapping();

    public D mapSourceToDestination(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public S mapDestinationToSource(D destination, Class<S> sourceClass) {
        return modelMapper.map(destination, sourceClass);
    }

    public void updateSourceFromDestination(S source, D destination) {
        modelMapper.map(destination, source);
    }
}