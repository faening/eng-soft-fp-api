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

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class TaxOrValueService extends AbstractService<TaxOrValueRequestDTO, TaxOrValueResponseDTO> {
    private final TaxOrValueRepository repository;
    private final TaxOrValueRequestMapper requestMapper;
    private final TaxOrValueResponseMapper responseMapper;

    private static final String VALIDATION_MESSAGE_TYPE = "taxOrValueService.validation.type";
    private static final String VALIDATION_MESSAGE_DESCRIPTION = "taxOrValueService.validation.description";
    private static final String VALIDATION_MESSAGE_MINIMUM_WAGE = "taxOrValueService.validation.minimumWage";
    private static final String VALIDATION_MESSAGE_DANGEROUSNESS_ALLOWANCE = "taxOrValueService.validation.DangerousnessAllowance";
    private static final String VALIDATION_MESSAGE_UNHEALTHINESS_ALLOWANCE = "taxOrValueService.validation.UnhealthinessAllowance";
    private static final String VALIDATION_MESSAGE_FAMILY_ALLOWANCE = "taxOrValueService.validation.FamilyAllowance";
    private static final String VALIDATION_MESSAGE_NIGHT_SHIFT_ALLOWANCE = "taxOrValueService.validation.NightShiftAllowance";
    private static final String VALIDATION_MESSAGE_TIME_SERVICE_ALLOWANCE = "taxOrValueService.validation.TimeServiceAllowance";
    private static final String VALIDATION_MESSAGE_DAYCARE_ALLOWANCE = "taxOrValueService.validation.DaycareAllowance";
    private static final String VALIDATION_MESSAGE_SALES_ALLOWANCE = "taxOrValueService.validation.SalesAllowance";

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

    /**
     * Este método recupera o valor do salário mínimo.
     *
     * @return O valor do salário mínimo.
     */
    public BigDecimal getMinimumWage() {
        return repository
            .findByType(TaxOrValueType.MINIMUM_WAGE)
            .stream()
            .map(TaxOrValue::getFixedValue)
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_MINIMUM_WAGE));
    }

    /**
     * Este método recupera objeto que representa o valor do adicional de periculosidade.
     *
     * @return O objeto TaxOrValueResponseDTO que representa o valor do adicional de periculosidade.
     */
    public TaxOrValueResponseDTO getDangerousnessAllowance() {
        return repository
            .findByType(TaxOrValueType.DANGEROUSNESS_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_DANGEROUSNESS_ALLOWANCE));
    }

    /**
     * Este método recupera uma lista de objetos que representam os percentuais de adicional por insalubridade.
     *
     * @return O objeto TaxOrValueResponseDTO que representa o percentual de adicional por insalubridade.
     */
    public List<TaxOrValueResponseDTO> getUnhealthinessAllowanceList() {
        return repository
            .findByType(TaxOrValueType.UNHEALTHINESS_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Este método recupera o objeto que representa o  percentual de adicional por insalubridade.
     *
     * @param rangeId O ID do percentual de adicional por insalubridade.
     * @return Retorna o objeto TaxOrValueResponseDTO que representa o percentual de adicional por insalubridade.
     */
    public TaxOrValueResponseDTO getUnhealthinessAllowanceByRangeId(Integer rangeId) {
        return getUnhealthinessAllowanceList()
            .stream()
            .filter(taxOrValue -> taxOrValue.getRange().equals(rangeId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_UNHEALTHINESS_ALLOWANCE));
    }

    /**
     * Este método recupera o objeto que representa o percemtual do salário família.
     *
     * @return O objeto TaxOrValueResponseDTO que representa o valor do adicional noturno.
     */
    public TaxOrValueResponseDTO getFamilyAllowance() {
        return repository
            .findByType(TaxOrValueType.FAMILY_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_FAMILY_ALLOWANCE));
    }

    /**
     * Este método recupera o objeto que representa o percentual de adicional noturno.
     *
     * @return O objeto TaxOrValueResponseDTO que representa o percentual do adicional noturno.
     */
    public TaxOrValueResponseDTO getNightShiftAllowance() {
        return repository
            .findByType(TaxOrValueType.NIGHT_SHIFT_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_NIGHT_SHIFT_ALLOWANCE));
    }

    /**
     * Este método recupera o objeto que representa o percentual do adicional por tempo de serviço.
     *
     * @return O objeto TaxOrValueResponseDTO que representa o percentual do adicional por tempo de serviço.
     */
    public TaxOrValueResponseDTO getTimeServiceAllowance() {
        return repository
            .findByType(TaxOrValueType.TIME_SERVICE_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_TIME_SERVICE_ALLOWANCE));
    }

    /**
     * Este método recupera o objeto que representa o valor do adicional de creche.
     *
     * @return O objeto TaxOrValueResponseDTO que representa o valor do adicional de creche.
     */
    public TaxOrValueResponseDTO getDaycareAllowance() {
        return repository
            .findByType(TaxOrValueType.DAYCARE_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_DAYCARE_ALLOWANCE));
    }

    /**
     * Este método recupera uma lista de objetos que representam os percentuais de comissão sobre vendas.
     *
     * @return Uma lista de objetos TaxOrValueResponseDTO que representam os percentuais de comissão sobre vendas.
     */
    public List<TaxOrValueResponseDTO> getSalesAllowanceList() {
        return repository
            .findByType(TaxOrValueType.SALES_ALLOWANCE)
            .stream()
            .map(taxOrValue -> responseMapper.toDTO(taxOrValue, TaxOrValueResponseDTO.class))
            .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Este método recupera o objeto que representa o percentual de comissão sobre vendas.
     * @param saleAmount O valor da venda.
     * @return O objeto TaxOrValueResponseDTO que representa o percentual de comissão sobre vendas.
     */
    public TaxOrValueResponseDTO getSalesAllowanceBySaleRange(BigDecimal saleAmount) {
        return getSalesAllowanceList()
            .stream()
            .filter(taxOrValue -> taxOrValue.getRangeMinimumWage().compareTo(saleAmount) <= 0
                               && taxOrValue.getRangeMaximumWage().compareTo(saleAmount) >= 0)
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(VALIDATION_MESSAGE_SALES_ALLOWANCE));
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
        if (request.getType() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_TYPE));
        if (request.getDescription() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_DESCRIPTION));
    }
}