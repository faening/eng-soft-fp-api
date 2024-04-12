package com.github.faening.eng_soft_fp_api.domain.mapper.employee_dependent;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.EmployeeDependent;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class EmployeeDependentRequestMapper extends AbstractMapper<EmployeeDependent, EmployeeDependentRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeDependentRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(EmployeeDependent.class, EmployeeDependentRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(EmployeeDependent::getEmployee, EmployeeDependentRequestDTO::setEmployeeId);

                mapper.when(notNull).map(src -> src.getPerson().getName(), EmployeeDependentRequestDTO::setName);
                mapper.when(notNull).map(src -> src.getPerson().getRg(), EmployeeDependentRequestDTO::setRg);
                mapper.when(notNull).map(src -> src.getPerson().getCpf(), EmployeeDependentRequestDTO::setCpf);
                mapper.when(notNull).map(src -> src.getPerson().getBirthDate(), EmployeeDependentRequestDTO::setBirthDate);
                mapper.when(notNull).map(src -> src.getPerson().getGender(), EmployeeDependentRequestDTO::setGender);

                mapper.when(notNull).map(EmployeeDependent::getSpecialNeeds, EmployeeDependentRequestDTO::setSpecialNeeds);
                mapper.when(notNull).map(EmployeeDependent::getFamilyAllowance, EmployeeDependentRequestDTO::setFamilyAllowance);
                mapper.when(notNull).map(EmployeeDependent::getDaycareAllowance, EmployeeDependentRequestDTO::setDaycareAllowance);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployeeConverter = context -> employeeService.getEmployeeById(context.getSource());

        modelMapper.createTypeMap(EmployeeDependentRequestDTO.class, EmployeeDependent.class)
            .addMappings(mapper -> {
                mapper.skip(EmployeeDependent::setId);
                mapper.skip(EmployeeDependent::setEntityMetadata);

                mapper.when(notNull).using(employeeIdToEmployeeConverter).map(EmployeeDependentRequestDTO::getEmployeeId, EmployeeDependent::setEmployee);

                mapper.when(notNull).<String>map(EmployeeDependentRequestDTO::getName, (dest, v) -> dest.getPerson().setName(v));
                mapper.when(notNull).<String>map(EmployeeDependentRequestDTO::getRg, (dest, v) -> dest.getPerson().setRg(v));
                mapper.when(notNull).<String>map(EmployeeDependentRequestDTO::getCpf, (dest, v) -> dest.getPerson().setCpf(v));
                mapper.when(notNull).<LocalDate>map(EmployeeDependentRequestDTO::getBirthDate, (dest, v) -> dest.getPerson().setBirthDate(v));
                mapper.when(notNull).<Gender>map(EmployeeDependentRequestDTO::getGender, (dest, v) -> dest.getPerson().setGender(v));

                mapper.when(notNull).map(EmployeeDependentRequestDTO::getSpecialNeeds, EmployeeDependent::setSpecialNeeds);
                mapper.when(notNull).map(EmployeeDependentRequestDTO::getFamilyAllowance, EmployeeDependent::setFamilyAllowance);
                mapper.when(notNull).map(EmployeeDependentRequestDTO::getDaycareAllowance, EmployeeDependent::setDaycareAllowance);
            });
    }
}
