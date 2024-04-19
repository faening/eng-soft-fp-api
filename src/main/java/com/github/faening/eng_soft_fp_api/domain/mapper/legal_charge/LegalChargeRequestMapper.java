package com.github.faening.eng_soft_fp_api.domain.mapper.legal_charge;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.LegalCharge;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.legal_charge.LegalChargeRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class LegalChargeRequestMapper extends AbstractMapper<LegalCharge, LegalChargeRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public LegalChargeRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(LegalCharge.class, LegalChargeRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getEmployee().getId(), LegalChargeRequestDTO::setEmployeeId);
                mapper.when(notNull).map(LegalCharge::getLegalChargeAmount, LegalChargeRequestDTO::setLegalChargeAmount);
                mapper.when(notNull).map(LegalCharge::getPercentage, LegalChargeRequestDTO::setPercentage);
                mapper.when(notNull).map(LegalCharge::getReleaseDate, LegalChargeRequestDTO::setReleaseDate);
                mapper.when(notNull).map(LegalCharge::getRecurring, LegalChargeRequestDTO::setRecurring);
                mapper.when(notNull).map(LegalCharge::getInstallments, LegalChargeRequestDTO::setInstallments);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployeeConverter = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(LegalChargeRequestDTO.class, LegalCharge.class)
            .addMappings(mapper -> {
                mapper.skip(LegalCharge::setId);
                mapper.skip(LegalCharge::setEntityMetadata);
                mapper.when(notNull).using(employeeIdToEmployeeConverter).map(LegalChargeRequestDTO::getEmployeeId, LegalCharge::setEmployee);
                mapper.when(notNull).map(LegalChargeRequestDTO::getLegalChargeAmount, LegalCharge::setLegalChargeAmount);
                mapper.when(notNull).map(LegalChargeRequestDTO::getPercentage, LegalCharge::setPercentage);
                mapper.when(notNull).map(LegalChargeRequestDTO::getReleaseDate, LegalCharge::setReleaseDate);
                mapper.when(notNull).map(LegalChargeRequestDTO::getRecurring, LegalCharge::setRecurring);
                mapper.when(notNull).map(LegalChargeRequestDTO::getInstallments, LegalCharge::setInstallments);
            });
    }
}