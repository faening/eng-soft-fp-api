package com.github.faening.eng_soft_fp_api.domain.mapper.company;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class CompanyResponseMapper extends AbstractMapper<Company, CompanyResponseDTO> {
    @Autowired
    public CompanyResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    public CompanyResponseDTO mapCompanyToCompanyResponseDTO(Company company) {
        return modelMapper.map(company, CompanyResponseDTO.class);
    }

    public List<CompanyResponseDTO> mapCompanyToCompanyResponseDTO(List<Company> companies) {
        return List.of(modelMapper.map(companies, CompanyResponseDTO[].class));
    }

    public Company mapCompanyResponseDTOToCompany(CompanyResponseDTO companyResponseDTO) {
        return modelMapper.map(companyResponseDTO, Company.class);
    }

    public List<Company> mapCompanyResponseDTOToCompany(List<CompanyResponseDTO> companyResponseDTOs) {
        return List.of(modelMapper.map(companyResponseDTOs, Company[].class));
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Company.class, CompanyResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Company::getId, CompanyResponseDTO::setId);
                mapper.when(notNull).map(Company::getCorporateName, CompanyResponseDTO::setCorporateName);
                mapper.when(notNull).map(Company::getTradeName, CompanyResponseDTO::setTradeName);
                mapper.when(notNull).map(Company::getCnpj, CompanyResponseDTO::setCnpj);
                mapper.when(notNull).map(Company::getIe, CompanyResponseDTO::setIe);
                mapper.when(notNull).map(Company::getOpeningDate, CompanyResponseDTO::setOpeningDate);
                mapper.when(notNull).map(Company::getPhone, CompanyResponseDTO::setPhone);
                mapper.when(notNull).map(Company::getEmail, CompanyResponseDTO::setEmail);

                mapper.when(notNull).map(src -> src.getAddress().getAddressStreet(), CompanyResponseDTO::setAddressStreet);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNumber(), CompanyResponseDTO::setAddressNumber);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNeighborhood(), CompanyResponseDTO::setAddressNeighborhood);
                mapper.when(notNull).map(src -> src.getAddress().getAddressComplement(), CompanyResponseDTO::setAddressComplement);
                mapper.when(notNull).map(src -> src.getAddress().getAddressCity(), CompanyResponseDTO::setAddressCity);
                mapper.when(notNull).map(src -> src.getAddress().getAddressUF(), CompanyResponseDTO::setAddressUF);
                mapper.when(notNull).map(src -> src.getAddress().getAddressZipCode(), CompanyResponseDTO::setAddressZipCode);

                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), CompanyResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), CompanyResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() { }
}