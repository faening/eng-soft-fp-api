package com.github.faening.eng_soft_fp_api.domain.mapper.benefit_voucher;

import com.github.faening.eng_soft_fp_api.data.model.BenefitVoucher;
import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.benefit_voucher.BenefitVoucherRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class BenefitVoucherRequestMapper extends AbstractMapper<BenefitVoucher, BenefitVoucherRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public BenefitVoucherRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(BenefitVoucher.class, BenefitVoucherRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(src -> src.getEmployee().getId(), BenefitVoucherRequestDTO::setEmployeeId);
                mapper.when(notNull).map(BenefitVoucher::getPaidValue, BenefitVoucherRequestDTO::setPaidValue);
                mapper.when(notNull).map(BenefitVoucher::getReleaseDate, BenefitVoucherRequestDTO::setReleaseDate);
                mapper.when(notNull).map(BenefitVoucher::getDescription, BenefitVoucherRequestDTO::setDescription);
                mapper.when(notNull).map(BenefitVoucher::getBenefitType, BenefitVoucherRequestDTO::setBenefitType);
                mapper.when(notNull).map(BenefitVoucher::getPaymentStatus, BenefitVoucherRequestDTO::setPaymentStatus);
                mapper.when(notNull).map(BenefitVoucher::getPayrollDeductible, BenefitVoucherRequestDTO::setPayrollDeductible);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployee = context -> employeeService.getEntityById(context.getSource());

        modelMapper.createTypeMap(BenefitVoucherRequestDTO.class, BenefitVoucher.class)
            .addMappings(mapper -> {
                mapper.skip(BenefitVoucher::setId);
                mapper.skip(BenefitVoucher::setEntityMetadata);
                mapper.when(notNull).using(employeeIdToEmployee).map(BenefitVoucherRequestDTO::getEmployeeId, BenefitVoucher::setEmployee);
                mapper.when(notNull).map(BenefitVoucherRequestDTO::getPaidValue, BenefitVoucher::setPaidValue);
                mapper.when(notNull).map(BenefitVoucherRequestDTO::getReleaseDate, BenefitVoucher::setReleaseDate);
                mapper.when(notNull).map(BenefitVoucherRequestDTO::getDescription, BenefitVoucher::setDescription);
                mapper.when(notNull).map(BenefitVoucherRequestDTO::getBenefitType, BenefitVoucher::setBenefitType);
                mapper.when(notNull).map(BenefitVoucherRequestDTO::getPaymentStatus, BenefitVoucher::setPaymentStatus);
                mapper.when(notNull).map(BenefitVoucherRequestDTO::getPayrollDeductible, BenefitVoucher::setPayrollDeductible);
            });
    }
}