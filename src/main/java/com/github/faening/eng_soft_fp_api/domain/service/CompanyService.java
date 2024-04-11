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
    private final CompanyRepository companyRepository;
    private final CompanyRequestMapper companyRequestMapper;
    private final CompanyResponseMapper companyResponseMapper;

    @Autowired
    public CompanyService(
        CompanyRepository companyRepository,
        CompanyRequestMapper companyRequestMapper,
        CompanyResponseMapper companyResponseMapper
    ) {
        this.companyRepository = companyRepository;
        this.companyRequestMapper = companyRequestMapper;
        this.companyResponseMapper = companyResponseMapper;
    }

    @Override
    public List<CompanyResponseDTO> getAll() {
        return null;
    }

    @Override
    public CompanyResponseDTO getById(Integer id) {
        validadeId(id);
        return companyResponseMapper.toDTO(searchCompanyById(id), CompanyResponseDTO.class);
    }

    @Override
    public CompanyResponseDTO create(CompanyRequestDTO request) {
        return null;
    }

    @Override
    public CompanyResponseDTO update(Integer id, CompanyRequestDTO request) {
        validadeId(id);
        Company company = searchCompanyById(id);
        companyRequestMapper.updateSourceFromDestination(company, request);
        Company updatedCompany = companyRepository.save(company);
        return companyResponseMapper.toDTO(updatedCompany, CompanyResponseDTO.class);
    }

    @Override
    public void delete(Integer id) {
    }

    public Company searchCompanyById(Integer id) {
        return companyRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhuma empresa encontrada com o ID informado: " + id)
        );
    }

    private void validadeId(Integer id) {
        if (id == null) throw new IllegalArgumentException(getLocalizedMessage("companyService.validation.companyId"));
    }
}