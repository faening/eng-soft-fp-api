package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.data.repository.CompanyRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;

        createCompanyToCompanyResponseDTOMapping();
        createCompanyRequestDTOToCompanyMapping();
    }

    private void createCompanyToCompanyResponseDTOMapping() {
        // As entidades `Address` e `EntityMetadata` foram embutidas na entidade `CompanyVO`.
        modelMapper.createTypeMap(Company.class, CompanyResponseDTO.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getAddress().getAddressStreet(), CompanyResponseDTO::setAddressStreet);
                mapper.map(src -> src.getAddress().getAddressNumber(), CompanyResponseDTO::setAddressNumber);
                mapper.map(src -> src.getAddress().getAddressComplement(), CompanyResponseDTO::setAddressComplement);
                mapper.map(src -> src.getAddress().getAddressCity(), CompanyResponseDTO::setAddressCity);
                mapper.map(src -> src.getAddress().getAddressUF(), CompanyResponseDTO::setAddressUF);
                mapper.map(src -> src.getAddress().getAddressZipCode(), CompanyResponseDTO::setAddressZipCode);
                mapper.map(src -> src.getEntityMetadata().getCreatedAt(), CompanyResponseDTO::setCreatedAt);
                mapper.map(src -> src.getEntityMetadata().getUpdatedAt(), CompanyResponseDTO::setUpdatedAt);
            });
    }

    private void createCompanyRequestDTOToCompanyMapping() {
        modelMapper.createTypeMap(CompanyRequestDTO.class, Company.class)
            .addMappings(mapper -> {
                mapper.map(CompanyRequestDTO::getCorporateName, Company::setCorporateName);
                mapper.map(CompanyRequestDTO::getTradeName, Company::setTradeName);
                mapper.map(CompanyRequestDTO::getPhone, Company::setPhone);
                mapper.map(CompanyRequestDTO::getEmail, Company::setEmail);
                mapper.<String>map(CompanyRequestDTO::getAddressStreet, (dest, v) -> dest.getAddress().setAddressStreet(v));
                mapper.<String>map(CompanyRequestDTO::getAddressNumber, (dest, v) -> dest.getAddress().setAddressNumber(v));
                mapper.<String>map(CompanyRequestDTO::getAddressComplement, (dest, v) -> dest.getAddress().setAddressComplement(v));
                mapper.<String>map(CompanyRequestDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.<BrazilianState>map(CompanyRequestDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.<String>map(CompanyRequestDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
            });
    }

    public CompanyResponseDTO getCompanyById(Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.map(company -> modelMapper.map(company, CompanyResponseDTO.class)).orElse(null);
    }

    public CompanyResponseDTO updateCompany(Integer id, CompanyRequestDTO companyRequestDTO) {
        // Verifica se a empresa existe. Caso não exista, retorna `null`
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty()) { return null; }

        // Atualiza os dados da empresa
        Company company = companyOptional.get();
        modelMapper.map(companyRequestDTO, company);
        company.setId(id);
        company.getEntityMetadata().setUpdatedAt(LocalDateTime.now());

        // Salva as alterações no banco de dados e retorna os dados atualizados
        companyRepository.save(company);
        return modelMapper.map(company, CompanyResponseDTO.class);
    }
}