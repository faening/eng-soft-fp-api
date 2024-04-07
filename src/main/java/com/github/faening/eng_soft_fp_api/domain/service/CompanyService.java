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

@Service
public class CompanyService {
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

    private Company searchCompanyById(Integer id) {
        return companyRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Nenhuma empresa encontrada com o ID informado: " + id)
        );
    }

    public CompanyResponseDTO getCompanyById(Integer id) {
        Company company = searchCompanyById(id);                               // Busca a empresa no banco de dados
        return companyResponseMapper.mapCompanyToCompanyResponseDTO(company);  // Converte a empresa para o CompanyResponseDTO e retorna
    }

    public CompanyResponseDTO updateCompany(Integer id, CompanyRequestDTO companyRequestDTO) {
        validateCompanyRequestDTO(companyRequestDTO);                                  // Valida se os campos do CompanyRequestDTO são válidos
        Company company = searchCompanyById(id);                                       // Busca a empresa no banco de dados
        companyRequestMapper.updateSourceFromDestination(company, companyRequestDTO);  // Atualiza os campos da empresa com os valores novos do CompanyRequestDTO
        companyRepository.save(company);                                               // Salva a empresa no banco de dados
        return companyResponseMapper.mapCompanyToCompanyResponseDTO(company);          // Converte a empresa para o CompanyResponseDTO e retorna
    }

    private void validateCompanyRequestDTO(CompanyRequestDTO companyRequestDTO) {
        List<String> unknownFields = companyRequestDTO.getUnknownFields();
        if (!unknownFields.isEmpty()) {
            throw new IllegalArgumentException("Os seguintes campos não são permitidos: " + String.join(", ", unknownFields));
        }
    }
}