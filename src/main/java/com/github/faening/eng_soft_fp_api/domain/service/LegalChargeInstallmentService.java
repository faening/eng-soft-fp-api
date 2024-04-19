package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import com.github.faening.eng_soft_fp_api.data.model.LegalChargeInstallment;
import com.github.faening.eng_soft_fp_api.data.repository.LegalChargeInstallmentRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge_installment.LegalChargeInstallmentRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge_installment.LegalChargeInstallmentResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment.LegalChargeInstallmentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge_installment.LegalChargeInstallmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class LegalChargeInstallmentService extends AbstractService<LegalChargeInstallmentRequestDTO, LegalChargeInstallmentResponseDTO> {
    private final LegalChargeInstallmentRepository repository;
    private final LegalChargeInstallmentRequestMapper requestMapper;
    private final LegalChargeInstallmentResponseMapper responseMapper;
    private final LegalChargeService legalChargeService;

    private static final String VALIDATION_MESSAGE_INSTALLMENT_NUMBER = "legalChargeInstallmentService.validation.installmentNumber";
    private static final String VALIDATION_MESSAGE_INSTALLMENT_VALUE = "legalChargeInstallmentService.validation.installmentValue";
    private static final String VALIDATION_MESSAGE_INSTALLMENT_MONTH = "legalChargeInstallmentService.validation.discountMonth";

    @Autowired
    public LegalChargeInstallmentService(
        LegalChargeInstallmentRepository repository,
        LegalChargeInstallmentRequestMapper requestMapper,
        LegalChargeInstallmentResponseMapper responseMapper,
        LegalChargeService legalChargeService
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.legalChargeService = legalChargeService;
    }

    @Override
    public List<LegalChargeInstallmentResponseDTO> getAll() {
        return repository
            .findAll()
            .stream()
            .map(legalChargeInstallment -> responseMapper.toDTO(legalChargeInstallment, LegalChargeInstallmentResponseDTO.class))
            .toList();
    }

    @Override
    public LegalChargeInstallmentResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchLegalChargeInstallmentById(id), LegalChargeInstallmentResponseDTO.class);
    }

    public List<LegalChargeInstallmentResponseDTO> getByLegalChargeId(Integer legalChargeId) {
        return searchLegalChargeInstallmentByLegalChargeId(legalChargeId);
    }

    public List<LegalChargeInstallment> getEntityByLegalChargeId(Integer legalChargeId) {
        return searchLegalChargeInstallmentEntityByLegalChargeId(legalChargeId);
    }

    @Override
    public LegalChargeInstallmentResponseDTO create(LegalChargeInstallmentRequestDTO request) {
        validate(request);
        LegalChargeInstallment legalChargeInstallment = requestMapper.toEntity(request, LegalChargeInstallment.class);
        LegalChargeInstallment savedLegalChargeInstallment = repository.save(legalChargeInstallment);
        return responseMapper.toDTO(savedLegalChargeInstallment, LegalChargeInstallmentResponseDTO.class);
    }

    @Override
    public LegalChargeInstallmentResponseDTO update(Integer id, LegalChargeInstallmentRequestDTO request) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        validate(id);
        repository.delete(searchLegalChargeInstallmentById(id));
    }

    private LegalChargeInstallment searchLegalChargeInstallmentById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    private List<LegalChargeInstallmentResponseDTO> searchLegalChargeInstallmentByLegalChargeId(Integer legalChargeId) {
        LegalCharge legalCharge = legalChargeService.getEntityById(legalChargeId);
        return repository
            .findByLegalCharge(legalCharge)
            .stream()
            .map(legalChargeInstallment -> responseMapper.toDTO(legalChargeInstallment, LegalChargeInstallmentResponseDTO.class))
            .toList();
    }

    private List<LegalChargeInstallment> searchLegalChargeInstallmentEntityByLegalChargeId(Integer legalChargeId) {
        LegalCharge legalCharge = legalChargeService.getEntityById(legalChargeId);
        return repository
            .findByLegalCharge(legalCharge)
            .stream()
            .toList();
    }

    @Override
    protected void validate(LegalChargeInstallmentRequestDTO request) {
        super.validate(request);
        if (request.getInstallmentNumber() == null)  throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_INSTALLMENT_NUMBER));
        if (request.getInstallmentValue() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_INSTALLMENT_VALUE));
        if (request.getDiscountMonth() == null) throw new IllegalArgumentException(getLocalizedMessage(VALIDATION_MESSAGE_INSTALLMENT_MONTH));
        if (request.getPaymentStatus() == null) request.setPaymentStatus(PaymentStatus.PENDING);
    }
}