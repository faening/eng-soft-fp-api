package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Rubric;
import com.github.faening.eng_soft_fp_api.data.repository.RubricRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.rubric.RubricRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.rubric.RubricResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class RubricService extends AbstractService<RubricRequestDTO, RubricResponseDTO> {
    private final RubricRepository repository;
    private final RubricRequestMapper requestMapper;
    private final RubricResponseMapper responseMapper;

    private static final String VALIDATION_MESSAGE_CODE = "rubricService.validation.code";
    private static final String VALIDATION_MESSAGE_NAME = "rubricService.validation.name";
    private static final String VALIDATION_MESSAGE_KIND = "rubricService.validation.kind";
    private static final String VALIDATION_MESSAGE_DESCRIPTION = "rubricService.validation.description";
    private static final String VALIDATION_MESSAGE_TYPE = "rubricService.validation.type";

    @Autowired
    public RubricService(
        RubricRepository repository,
        RubricRequestMapper requestMapper,
        RubricResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<RubricResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(rubric -> responseMapper.toDTO(rubric, RubricResponseDTO.class))
            .toList();
    }

    @Override
    public RubricResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(getEntityById(id), RubricResponseDTO.class);
    }

    public Rubric getEntityById(Integer id) {
        validate(id);
        return searchRubricEntityById(id);
    }

    public RubricResponseDTO getByCode(Integer code) {
        validate(code);
        return responseMapper.toDTO(getEntityByCode(code), RubricResponseDTO.class);
    }

    public Rubric getEntityByCode(Integer code) {
        validate(code);
        return searchRubricEntityByCode(code);
    }

    @Override
    public RubricResponseDTO create(RubricRequestDTO request) {
        validate(request);
        Rubric rubric = requestMapper.toEntity(request, Rubric.class);
        Rubric savedRubric = repository.save(rubric);
        return responseMapper.toDTO(savedRubric, RubricResponseDTO.class);
    }

    @Override
    public RubricResponseDTO update(Integer id, RubricRequestDTO request) {
        validate(id);
        validate(request);
        Rubric rubric = searchRubricEntityById(id);
        requestMapper.updateSourceFromDestination(rubric, request);
        Rubric updatedRubric = repository.save(rubric);
        return responseMapper.toDTO(updatedRubric, RubricResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.deleteById(id);
    }

    private Rubric searchRubricEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(ID_VALIDATION_MESSAGE)
        );
    }

    private Rubric searchRubricEntityByCode(Integer code) {
        return repository.findByCode(code) == null ? null : repository.findByCode(code);
    }

    @Override
    public void validate(RubricRequestDTO request) {
        super.validate(request);
        if (request.getCode() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_CODE));
        if (request.getName() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_NAME));
        if (request.getKind() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_KIND));
        if (request.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_DESCRIPTION));
        if (request.getType() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_TYPE));
    }
}