package com.github.faening.eng_soft_fp_api.domain.mapper.company;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.domain.enumeration.BrazilianState;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.company.CompanyResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class CompanyResponseMapper extends AbstractMapper<Company, CompanyResponseDTO> {
    @Autowired
    public CompanyResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
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
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(CompanyResponseDTO.class, Company.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(CompanyResponseDTO::getId, Company::setId);
                mapper.when(notNull).map(CompanyResponseDTO::getCorporateName, Company::setCorporateName);
                mapper.when(notNull).map(CompanyResponseDTO::getTradeName, Company::setTradeName);
                mapper.when(notNull).map(CompanyResponseDTO::getCnpj, Company::setCnpj);
                mapper.when(notNull).map(CompanyResponseDTO::getIe, Company::setIe);
                mapper.when(notNull).map(CompanyResponseDTO::getOpeningDate, Company::setOpeningDate);
                mapper.when(notNull).map(CompanyResponseDTO::getPhone, Company::setPhone);
                mapper.when(notNull).map(CompanyResponseDTO::getEmail, Company::setEmail);
                mapper.when(notNull).<String>map(CompanyResponseDTO::getAddressStreet, (dest, v) -> dest.getAddress().setAddressStreet(v));
                mapper.when(notNull).<String>map(CompanyResponseDTO::getAddressNumber, (dest, v) -> dest.getAddress().setAddressNumber(v));
                mapper.when(notNull).<String>map(CompanyResponseDTO::getAddressNeighborhood, (dest, v) -> dest.getAddress().setAddressNeighborhood(v));
                mapper.when(notNull).<String>map(CompanyResponseDTO::getAddressComplement, (dest, v) -> dest.getAddress().setAddressComplement(v));
                mapper.when(notNull).<String>map(CompanyResponseDTO::getAddressCity, (dest, v) -> dest.getAddress().setAddressCity(v));
                mapper.when(notNull).<BrazilianState>map(CompanyResponseDTO::getAddressUF, (dest, v) -> dest.getAddress().setAddressUF(v));
                mapper.when(notNull).<String>map(CompanyResponseDTO::getAddressZipCode, (dest, v) -> dest.getAddress().setAddressZipCode(v));
                mapper.when(notNull).<LocalDateTime>map(CompanyResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(CompanyResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}