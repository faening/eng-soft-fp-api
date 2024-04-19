package com.github.faening.eng_soft_fp_api.domain.mapper.union_contribution;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.UnionContribution;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.union_contribution.UnionContributionResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class UnionContributionResponseMapper extends AbstractMapper<UnionContribution, UnionContributionResponseDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public UnionContributionResponseMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(UnionContribution.class, UnionContributionResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(UnionContribution::getId, UnionContributionResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), UnionContributionResponseDTO::setEmployeeId);
                mapper.when(notNull).map(UnionContribution::getReleaseYear, UnionContributionResponseDTO::setReleaseYear);
                mapper.when(notNull).map(UnionContribution::getOptedOut, UnionContributionResponseDTO::setOptedOut);
                mapper.when(notNull).map(UnionContribution::getPaymentStatus, UnionContributionResponseDTO::setPaymentStatus);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), UnionContributionResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), UnionContributionResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(UnionContributionResponseDTO.class, UnionContribution.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(UnionContributionResponseDTO::getId, UnionContribution::setId);
                mapper.when(notNull).using(employeeIdToEmployee).map(UnionContributionResponseDTO::getEmployeeId, UnionContribution::setEmployee);
                mapper.when(notNull).map(UnionContributionResponseDTO::getReleaseYear, UnionContribution::setReleaseYear);
                mapper.when(notNull).map(UnionContributionResponseDTO::getOptedOut, UnionContribution::setOptedOut);
                mapper.when(notNull).map(UnionContributionResponseDTO::getPaymentStatus, UnionContribution::setPaymentStatus);
                mapper.when(notNull).<LocalDateTime>map(UnionContributionResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(UnionContributionResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}