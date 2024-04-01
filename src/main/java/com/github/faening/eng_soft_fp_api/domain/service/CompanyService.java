package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.data.repository.CompanyRepository;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import com.github.faening.eng_soft_fp_api.exception.ResourceNotFoundException;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        modelMapper.createTypeMap(Company.class, CompanyResponseDTO.class)
            .addMappings(mapper -> {
                mapper.map(Company::getId, CompanyResponseDTO::setId);
                mapper.map(Company::getCorporateName, CompanyResponseDTO::setCorporateName);
                mapper.map(Company::getTradeName, CompanyResponseDTO::setTradeName);
                mapper.map(Company::getCnpj, CompanyResponseDTO::setCnpj);
                mapper.map(Company::getIe, CompanyResponseDTO::setIe);
                mapper.map(Company::getOpeningDate, CompanyResponseDTO::setOpeningDate);
                mapper.map(Company::getPhone, CompanyResponseDTO::setPhone);
                mapper.map(Company::getEmail, CompanyResponseDTO::setEmail);
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
        @SuppressWarnings("rawtypes")
        Condition notNull = ctx -> ctx.getSource() != null;

        modelMapper.createTypeMap(CompanyRequestDTO.class, Company.class)
            .addMappings(mapper -> {
                mapper.skip(Company::setId);
                mapper.skip(Company::setCnpj);
                mapper.skip(Company::setIe);
                mapper.skip(Company::setOpeningDate);
                mapper.skip(Company::setEntityMetadata);
                mapper.when(notNull).map(CompanyRequestDTO::getCorporateName, Company::setCorporateName);
                mapper.when(notNull).map(CompanyRequestDTO::getTradeName, Company::setTradeName);
                mapper.when(notNull).map(CompanyRequestDTO::getPhone, Company::setPhone);
                mapper.when(notNull).map(CompanyRequestDTO::getEmail, Company::setEmail);
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressStreet, (dest, v) -> dest.getAddress().setAddressStreet(v));
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressNumber, (dest, v) -> dest.getAddress().setAddressNumber(v));
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressComplement, (dest, v) -> dest.getAddress().setAddressComplement(v));
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.when(notNull).<BrazilianState>map(CompanyRequestDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
            });
    }

    public CompanyResponseDTO getCompanyById(Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.map(company -> modelMapper.map(company, CompanyResponseDTO.class)).orElse(null);
    }

    public CompanyResponseDTO updateCompany(Integer id, CompanyRequestDTO companyRequestDTO) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada"));
        modelMapper.map(companyRequestDTO, company);
        companyRepository.save(company);
        return modelMapper.map(company, CompanyResponseDTO.class);
    }
}