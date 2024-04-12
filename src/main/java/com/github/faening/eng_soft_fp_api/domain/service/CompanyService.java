package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.data.repository.CompanyRepository;
import com.github.faening.eng_soft_fp_api.domain.mapper.company.CompanyRequestMapper;
import com.github.faening.eng_soft_fp_api.domain.mapper.company.CompanyResponseMapper;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class CompanyService extends AbstractService<CompanyRequestDTO, CompanyResponseDTO> {
    private final CompanyRepository repository;
    private final CompanyRequestMapper requestMapper;
    private final CompanyResponseMapper responseMapper;

    @Autowired
    public CompanyService(
        CompanyRepository repository,
        CompanyRequestMapper requestMapper,
        CompanyResponseMapper responseMapper
    ) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<CompanyResponseDTO> getAll() {
        return null;
    }

    @Override
    public CompanyResponseDTO getById(Integer id) {
        validate(id);
        return responseMapper.toDTO(searchCompanyEntityById(id), CompanyResponseDTO.class);
    }

    public Company getEntityById(Integer id) {
        validate(id);
        return searchCompanyEntityById(id);
    }

    @Override
    public CompanyResponseDTO create(CompanyRequestDTO request) {
        return null;
    }

    @Override
    public CompanyResponseDTO update(Integer id, CompanyRequestDTO request) {
        validate(id);
        validate(request);
        Company company = searchCompanyEntityById(id);
        requestMapper.updateSourceFromDestination(company, request);
        Company updatedCompany = repository.save(company);
        return responseMapper.toDTO(updatedCompany, CompanyResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
    }

    private Company searchCompanyEntityById(Integer id) {
        return repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(getLocalizedMessage(ID_VALIDATION_MESSAGE))
        );
    }
}