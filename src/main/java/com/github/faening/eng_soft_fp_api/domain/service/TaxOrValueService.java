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

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class TaxOrValueService extends AbstractService<TaxOrValueRequestDTO, TaxOrValueResponseDTO> {
    private final TaxOrValueRepository taxOrValueRepository;
    private final TaxOrValueRequestMapper taxOrValueRequestMapper;
    private final TaxOrValueResponseMapper taxOrValueResponseMapper;

    @Autowired
    public TaxOrValueService(
        TaxOrValueRepository taxOrValueRepository,
        TaxOrValueRequestMapper taxOrValueRequestMapper,
        TaxOrValueResponseMapper taxOrValueResponseMapper
    ) {
        this.taxOrValueRepository = taxOrValueRepository;
        this.taxOrValueRequestMapper = taxOrValueRequestMapper;
        this.taxOrValueResponseMapper = taxOrValueResponseMapper;
    }

    @Override
    public List<TaxOrValueResponseDTO> getAll() {
        return taxOrValueRepository
            .findAll()
            .stream()
            .map(taxOrValue -> taxOrValueResponseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public TaxOrValueResponseDTO getById(Integer id) {
        validateId(id);
        return taxOrValueResponseMapper.toDTO(searchTaxOrValueById(id), TaxOrValueResponseDTO.class);
    }

    @Override
    public TaxOrValueResponseDTO create(TaxOrValueRequestDTO request) {
        validateTaxOrValueRequestDTO(request);
        TaxOrValue taxOrValue = taxOrValueRequestMapper.toEntity(request, TaxOrValue.class);
        TaxOrValue savedTaxOrValue = taxOrValueRepository.save(taxOrValue);
        return taxOrValueResponseMapper.toDTO(savedTaxOrValue, TaxOrValueResponseDTO.class);
    }

    @Override
    public TaxOrValueResponseDTO update(Integer id, TaxOrValueRequestDTO request) {
        validateId(id);
        validateTaxOrValueRequestDTO(request);
        TaxOrValue taxOrValue = searchTaxOrValueById(id);
        taxOrValueRequestMapper.updateSourceFromDestination(taxOrValue, request);
        TaxOrValue updatedTaxOrValue = taxOrValueRepository.save(taxOrValue);
        return taxOrValueResponseMapper.toDTO(updatedTaxOrValue, TaxOrValueResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validateId(id);
        taxOrValueRepository.deleteById(id);
    }

    /**
     * Busca um imposto ou valor pelo tipo de imposto ou valor
     *
     * @param type Tipo de imposto ou valor
     *
     * @return TaxOrValueResponseDTO
     */
    public List<TaxOrValueResponseDTO> getByType(TaxOrValueType type) {
        return taxOrValueRepository
            .findByType(type)
            .stream()
            .map(taxOrValue -> taxOrValueResponseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .collect(java.util.stream.Collectors.toList()
        );
    }

    public TaxOrValue searchTaxOrValueById(Integer id) {
        return taxOrValueRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhum imposto ou valor encontrado com o id: " + id)
        );
    }

    private void validateId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage("taxOrValueService.validation.taxOrValueId"));
    }

    private void validateTaxOrValueRequestDTO(TaxOrValueRequestDTO request) {
        if (request.getType() == null) throw new IllegalArgumentException(getLocalizedMessage("taxOrValueService.validation.type"));
        if (request.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage("taxOrValueService.validation.description"));
    }
}
