package com.github.faening.eng_soft_fp_api.domain.mapper.payroll;

import com.github.faening.eng_soft_fp_api.data.model.Company;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Payroll;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.payroll.PayrollRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.CompanyService;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unused"})
@Service
public class PayrollRequestMapper extends AbstractMapper<Payroll, PayrollRequestDTO> {
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public PayrollRequestMapper(
        ModelMapper modelMapper,
        CompanyService companyService,
        EmployeeService employeeService
    ) {
        super(modelMapper);
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Payroll.class, PayrollRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getCompany().getId(), PayrollRequestDTO::setCompanyId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), PayrollRequestDTO::setEmployeeId);
                mapper.when(notNull).map(Payroll::getMonth, PayrollRequestDTO::setMonth);
                mapper.when(notNull).map(Payroll::getYear, PayrollRequestDTO::setYear);
                mapper.when(notNull).map(Payroll::getGrossSalary, PayrollRequestDTO::setGrossSalary);
                mapper.when(notNull).map(Payroll::getInssBaseAmount, PayrollRequestDTO::setInssBaseAmount);
                mapper.when(notNull).map(Payroll::getInssTaxRate, PayrollRequestDTO::setInssTaxRate);
                mapper.when(notNull).map(Payroll::getIrrfBaseAmount, PayrollRequestDTO::setIrrfBaseAmount);
                mapper.when(notNull).map(Payroll::getIrrfTaxRate, PayrollRequestDTO::setIrrfTaxRate);
                mapper.when(notNull).map(Payroll::getFgtsPayed, PayrollRequestDTO::setFgtsPayed);
                mapper.when(notNull).map(Payroll::getTotalAdditions, PayrollRequestDTO::setTotalAdditions);
                mapper.when(notNull).map(Payroll::getTotalDiscounts, PayrollRequestDTO::setTotalDiscounts);
                mapper.when(notNull).map(Payroll::getTotalLiquid, PayrollRequestDTO::setTotalLiquid);
                mapper.when(notNull).map(Payroll::getItems, PayrollRequestDTO::setItems);
                mapper.when(notNull).map(Payroll::getStatus, PayrollRequestDTO::setStatus);
                mapper.when(notNull).map(Payroll::getNotes, PayrollRequestDTO::setNotes);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Company> companyIdToCompanyConverter = ctx -> companyService.getEntityById(ctx.getSource());
        Converter<Integer, Employee> employeeIdToEmployeeConverter = ctx -> employeeService.getEntityById(ctx.getSource());

        // https://stackoverflow.com/questions/49831753/modelmapper-matches-multiple-source-property-hierarchies
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        // modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.createTypeMap(PayrollRequestDTO.class, Payroll.class)
            .addMappings(mapper -> {
                mapper.skip(Payroll::setId);
                mapper.skip(Payroll::setEntityMetadata);
                mapper.when(notNull).using(companyIdToCompanyConverter).map(PayrollRequestDTO::getCompanyId, Payroll::setCompany);
                mapper.when(notNull).using(employeeIdToEmployeeConverter).map(PayrollRequestDTO::getEmployeeId, Payroll::setEmployee);
                mapper.when(notNull).map(PayrollRequestDTO::getMonth, Payroll::setMonth);
                mapper.when(notNull).map(PayrollRequestDTO::getYear, Payroll::setYear);
                mapper.when(notNull).map(PayrollRequestDTO::getGrossSalary, Payroll::setGrossSalary);
                mapper.when(notNull).map(PayrollRequestDTO::getInssBaseAmount, Payroll::setInssBaseAmount);
                mapper.when(notNull).map(PayrollRequestDTO::getInssTaxRate, Payroll::setInssTaxRate);
                mapper.when(notNull).map(PayrollRequestDTO::getIrrfBaseAmount, Payroll::setIrrfBaseAmount);
                mapper.when(notNull).map(PayrollRequestDTO::getIrrfTaxRate, Payroll::setIrrfTaxRate);
                mapper.when(notNull).map(PayrollRequestDTO::getFgtsPayed, Payroll::setFgtsPayed);
                mapper.when(notNull).map(PayrollRequestDTO::getTotalAdditions, Payroll::setTotalAdditions);
                mapper.when(notNull).map(PayrollRequestDTO::getTotalDiscounts, Payroll::setTotalDiscounts);
                mapper.when(notNull).map(PayrollRequestDTO::getTotalLiquid, Payroll::setTotalLiquid);
                mapper.when(notNull).map(PayrollRequestDTO::getItems, Payroll::setItems);
                mapper.when(notNull).map(PayrollRequestDTO::getStatus, Payroll::setStatus);
                mapper.when(notNull).map(PayrollRequestDTO::getNotes, Payroll::setNotes);
            });
    }
}