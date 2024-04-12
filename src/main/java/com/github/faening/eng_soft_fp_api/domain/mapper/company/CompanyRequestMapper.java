package com.github.faening.eng_soft_fp_api.domain.mapper.company;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class CompanyRequestMapper extends AbstractMapper<Company, CompanyRequestDTO> {
    @Autowired
    public CompanyRequestMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Company.class, CompanyRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Company::getCorporateName, CompanyRequestDTO::setCorporateName);
                mapper.when(notNull).map(Company::getTradeName, CompanyRequestDTO::setTradeName);
                mapper.when(notNull).map(Company::getPhone, CompanyRequestDTO::setPhone);
                mapper.when(notNull).map(Company::getEmail, CompanyRequestDTO::setEmail);

                mapper.when(notNull).map(src -> src.getAddress().getAddressStreet(), CompanyRequestDTO::setAddressStreet);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNumber(), CompanyRequestDTO::setAddressNumber);
                mapper.when(notNull).map(src -> src.getAddress().getAddressNeighborhood(), CompanyRequestDTO::setAddressNeighborhood);
                mapper.when(notNull).map(src -> src.getAddress().getAddressComplement(), CompanyRequestDTO::setAddressComplement);
                mapper.when(notNull).map(src -> src.getAddress().getAddressCity(), CompanyRequestDTO::setAddressCity);
                mapper.when(notNull).map(src -> src.getAddress().getAddressUF(), CompanyRequestDTO::setAddressUF);
                mapper.when(notNull).map(src -> src.getAddress().getAddressZipCode(), CompanyRequestDTO::setAddressZipCode);
            });
    }

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
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressNeighborhood, (dest, v) -> dest.getAddress().setAddressNeighborhood(v));
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.when(notNull).<BrazilianState>map(CompanyRequestDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.when(notNull).<String>map(CompanyRequestDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
            });
    }
}