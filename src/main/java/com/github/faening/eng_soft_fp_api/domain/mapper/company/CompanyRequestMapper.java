package com.github.faening.eng_soft_fp_api.domain.mapper.company;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class CompanyRequestMapper extends AbstractMapper<Company, CompanyRequestDTO> {
    @Autowired
    public CompanyRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    public CompanyRequestDTO mapCompanyToCompanyRequestDTO(Company company) {
        return modelMapper.map(company, CompanyRequestDTO.class);
    }

    public List<CompanyRequestDTO> mapCompanyToCompanyRequestDTO(List<Company> companies) {
        return List.of(modelMapper.map(companies, CompanyRequestDTO[].class));
    }

    public Company mapCompanyRequestDTOToCompany(CompanyRequestDTO companyRequestDTO) {
        return modelMapper.map(companyRequestDTO, Company.class);
    }

    public List<Company> mapCompanyRequestDTOToCompany(List<CompanyRequestDTO> companyRequestDTOs) {
        return List.of(modelMapper.map(companyRequestDTOs, Company[].class));
    }

    @Override
    protected void createSourceToDestinationMapping() { }

    @Override
    protected void createDestinationToSourceMapping() {
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
}