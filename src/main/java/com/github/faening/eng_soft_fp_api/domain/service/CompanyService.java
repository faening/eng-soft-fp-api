package com.github.faening.eng_soft_fp_api.domain.service;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.data.repository.CompanyRepository;
import com.github.faening.eng_soft_fp_api.domain.model.CompanyVO;
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

        /*
        * As entidades `Address` e `EntityMetadata` foram embutidas na entidade `CompanyVO`.
        * */
        modelMapper.createTypeMap(Company.class, CompanyVO.class)
            .addMappings(mapper -> {
                mapper.map(src -> src.getAddress().getAddressStreet(), CompanyVO::setAddressStreet);
                mapper.map(src -> src.getAddress().getAddressNumber(), CompanyVO::setAddressNumber);
                mapper.map(src -> src.getAddress().getAddressComplement(), CompanyVO::setAddressComplement);
                mapper.map(src -> src.getAddress().getAddressCity(), CompanyVO::setAddressCity);
                mapper.map(src -> src.getAddress().getAddressUF(), CompanyVO::setAddressUF);
                mapper.map(src -> src.getAddress().getAddressZipCode(), CompanyVO::setAddressZipCode);
                mapper.map(src -> src.getEntityMetadata().getCreatedAt(), CompanyVO::setCreatedAt);
                mapper.map(src -> src.getEntityMetadata().getUpdatedAt(), CompanyVO::setUpdatedAt);
            });
    }

    public CompanyVO getCompanyById(Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.map(company -> modelMapper.map(company, CompanyVO.class)).orElse(null);
    }
}