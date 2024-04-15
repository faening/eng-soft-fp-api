package com.github.faening.eng_soft_fp_api.domain.mapper.employee_dependent;

import com.github.faening.eng_soft_fp_api.data.model.EmployeeDependent;
import com.github.faening.eng_soft_fp_api.domain.enumeration.Gender;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.employee_dependent.EmployeeDependentResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
@Service
public class EmployeeDependentResponseMapper extends AbstractMapper<EmployeeDependent, EmployeeDependentResponseDTO> {
    @Autowired
    public EmployeeDependentResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(EmployeeDependent.class, EmployeeDependentResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(EmployeeDependent::getId, EmployeeDependentResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), EmployeeDependentResponseDTO::setEmployeeId);
                mapper.when(notNull).map(src -> src.getPerson().getName(), EmployeeDependentResponseDTO::setName);
                mapper.when(notNull).map(src -> src.getPerson().getRg(), EmployeeDependentResponseDTO::setRg);
                mapper.when(notNull).map(src -> src.getPerson().getCpf(), EmployeeDependentResponseDTO::setCpf);
                mapper.when(notNull).map(src -> src.getPerson().getBirthDate(), EmployeeDependentResponseDTO::setBirthDate);
                mapper.when(notNull).map(src -> src.getPerson().getGender(), EmployeeDependentResponseDTO::setGender);
                mapper.when(notNull).map(EmployeeDependent::getSpecialNeeds, EmployeeDependentResponseDTO::setSpecialNeeds);
                mapper.when(notNull).map(EmployeeDependent::getFamilyAllowance, EmployeeDependentResponseDTO::setFamilyAllowance);
                mapper.when(notNull).map(EmployeeDependent::getDaycareAllowance, EmployeeDependentResponseDTO::setDaycareAllowance);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), EmployeeDependentResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), EmployeeDependentResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(EmployeeDependentResponseDTO.class, EmployeeDependent.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(EmployeeDependentResponseDTO::getId, EmployeeDependent::setEmployee);
                mapper.when(notNull).map(EmployeeDependentResponseDTO::getEmployeeId, EmployeeDependent::setEmployee);
                mapper.when(notNull).<String>map(EmployeeDependentResponseDTO::getName, (dest, v) -> dest.getPerson().setName(v));
                mapper.when(notNull).<String>map(EmployeeDependentResponseDTO::getRg, (dest, v) -> dest.getPerson().setRg(v));
                mapper.when(notNull).<String>map(EmployeeDependentResponseDTO::getCpf, (dest, v) -> dest.getPerson().setCpf(v));
                mapper.when(notNull).<LocalDate>map(EmployeeDependentResponseDTO::getBirthDate, (dest, v) -> dest.getPerson().setBirthDate(v));
                mapper.when(notNull).<Gender>map(EmployeeDependentResponseDTO::getGender, (dest, v) -> dest.getPerson().setGender(v));
                mapper.when(notNull).map(EmployeeDependentResponseDTO::getSpecialNeeds, EmployeeDependent::setSpecialNeeds);
                mapper.when(notNull).map(EmployeeDependentResponseDTO::getFamilyAllowance, EmployeeDependent::setFamilyAllowance);
                mapper.when(notNull).map(EmployeeDependentResponseDTO::getDaycareAllowance, EmployeeDependent::setDaycareAllowance);
                mapper.when(notNull).<LocalDateTime>map(EmployeeDependentResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(EmployeeDependentResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}