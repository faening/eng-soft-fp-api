package com.github.faening.eng_soft_fp_api.domain.mapper.union_contribution;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.UnionContribution;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.union_contribution.UnionContributionRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class UnionContributionRequestMapper extends AbstractMapper<UnionContribution, UnionContributionRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public UnionContributionRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(UnionContribution.class, UnionContributionRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getEmployee().getId(), UnionContributionRequestDTO::setEmployeeId);
                mapper.when(notNull).map(UnionContribution::getReleaseYear, UnionContributionRequestDTO::setReleaseYear);
                mapper.when(notNull).map(UnionContribution::getOptedOut, UnionContributionRequestDTO::setOptedOut);
                mapper.when(notNull).map(UnionContribution::getPaymentStatus, UnionContributionRequestDTO::setPaymentStatus);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(UnionContributionRequestDTO.class, UnionContribution.class)
            .addMappings(mapper -> {
                mapper.skip(UnionContribution::setId);
                mapper.skip(UnionContribution::setEntityMetadata);
                mapper.when(notNull).using(employeeIdToEmployee).map(UnionContributionRequestDTO::getEmployeeId, UnionContribution::setEmployee);
                mapper.when(notNull).map(UnionContributionRequestDTO::getReleaseYear, UnionContribution::setReleaseYear);
                mapper.when(notNull).map(UnionContributionRequestDTO::getOptedOut, UnionContribution::setOptedOut);
                mapper.when(notNull).map(UnionContributionRequestDTO::getPaymentStatus, UnionContribution::setPaymentStatus);
            });
    }
}
