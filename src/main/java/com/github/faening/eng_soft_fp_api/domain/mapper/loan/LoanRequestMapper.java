package com.github.faening.eng_soft_fp_api.domain.mapper.loan;

import com.github.faening.eng_soft_fp_api.data.model.Employee;
import com.github.faening.eng_soft_fp_api.data.model.Loan;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.service.EmployeeService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
@Service
public class LoanRequestMapper extends AbstractMapper<Loan, LoanRequestDTO> {
    private final EmployeeService employeeService;

    @Autowired
    public LoanRequestMapper(ModelMapper modelMapper, EmployeeService employeeService) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Loan.class, LoanRequestDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Loan::getEmployee, LoanRequestDTO::setEmployeeId);
                mapper.when(notNull).map(Loan::getLoanAmountValue, LoanRequestDTO::setLoanAmountValue);
                mapper.when(notNull).map(Loan::getInstallmentQuantity, LoanRequestDTO::setInstallmentQuantity);
                mapper.when(notNull).map(Loan::getRequestDate, LoanRequestDTO::setRequestDate);
                mapper.when(notNull).map(Loan::getApprovalDate, LoanRequestDTO::setApprovalDate);
                mapper.when(notNull).map(Loan::getCompanyPaymentDate, LoanRequestDTO::setCompanyPaymentDate);
                mapper.when(notNull).map(Loan::getPaymentStatus, LoanRequestDTO::setPaymentStatus);
                mapper.when(notNull).map(Loan::getInstallments, LoanRequestDTO::setInstallments);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        Converter<Integer, Employee> employeeIdToEmployeeConverter = context -> employeeService.getEmployeeEntityById(context.getSource());

        modelMapper.createTypeMap(LoanRequestDTO.class, Loan.class)
            .addMappings(mapper -> {
                mapper.skip(Loan::setId);
                mapper.skip(Loan::setEntityMetadata);
                mapper.when(notNull).using(employeeIdToEmployeeConverter).map(LoanRequestDTO::getEmployeeId, Loan::setEmployee);
                mapper.when(notNull).map(LoanRequestDTO::getLoanAmountValue, Loan::setLoanAmountValue);
                mapper.when(notNull).map(LoanRequestDTO::getInstallmentQuantity, Loan::setInstallmentQuantity);
                mapper.when(notNull).map(LoanRequestDTO::getRequestDate, Loan::setRequestDate);
                mapper.when(notNull).map(LoanRequestDTO::getApprovalDate, Loan::setApprovalDate);
                mapper.when(notNull).map(LoanRequestDTO::getCompanyPaymentDate, Loan::setCompanyPaymentDate);
                mapper.when(notNull).map(LoanRequestDTO::getPaymentStatus, Loan::setPaymentStatus);
                mapper.when(notNull).map(LoanRequestDTO::getInstallments, Loan::setInstallments);
            });
    }
}