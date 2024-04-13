package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.TaxOrValue;
import com.github.faening.eng_soft_fp_api.data.repository.TaxOrValueRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.TaxOrValueType;
import com.github.faening.eng_soft_fp_api.domain.mapper.tax_or_value.TaxOrValueRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.tax_or_value.TaxOrValueResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.tax_or_value.TaxOrValueResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class TaxOrValueService extends AbstractService<TaxOrValueRequestDTO, TaxOrValueResponseDTO> {
    private final TaxOrValueRepository repository;
    private final TaxOrValueRequestMapper requestMapper;
    private final TaxOrValueResponseMapper responseMapper;

    private static final String TOV_TYPE_VALIDATION_MESSAGE = "taxOrValueService.validation.type";
    private static final String TOV_DESCRIPTION_VALIDATION_MESSAGE = "taxOrValueService.validation.description";

    @Autowired
    public TaxOrValueService(
        TaxOrValueRepository repository,
        TaxOrValueRequestMapper requestMapper,
        TaxOrValueResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<TaxOrValueResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public TaxOrValueResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchTaxOrValueEntityById(id), TaxOrValueResponseDTO.class);
    }

    public TaxOrValue getEntityById(Integer id) {
        validate(id);
        return searchTaxOrValueEntityById(id);
    }

    public List<TaxOrValueResponseDTO> getByType(TaxOrValueType type) {
        return repository
            .findByType(type)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .collect(java.util.stream.Collectors.toList()
            );
    }

    @Override
    public TaxOrValueResponseDTO create(TaxOrValueRequestDTO request) {
        validate(request);
        TaxOrValue taxOrValue = requestMapper.toEntity(request, TaxOrValue.class);
        TaxOrValue savedTaxOrValue = repository.save(taxOrValue);
        return responseMapper.toDTO(savedTaxOrValue, TaxOrValueResponseDTO.class);
    }

    @Override
    public TaxOrValueResponseDTO update(Integer id, TaxOrValueRequestDTO request) {
        validate(id);
        validate(request);
        TaxOrValue taxOrValue = searchTaxOrValueEntityById(id);
        requestMapper.updateSourceFromDestination(taxOrValue, request);
        TaxOrValue updatedTaxOrValue = repository.save(taxOrValue);
        return responseMapper.toDTO(updatedTaxOrValue, TaxOrValueResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private TaxOrValue searchTaxOrValueEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    @Override
    protected void validate(TaxOrValueRequestDTO request) {
        super.validate(request);
        if (request.getType() == null) throw new IllegalArgumentException(getLocalizedMessage(TOV_TYPE_VALIDATION_MESSAGE));
        if (request.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage(TOV_DESCRIPTION_VALIDATION_MESSAGE));
    }
}