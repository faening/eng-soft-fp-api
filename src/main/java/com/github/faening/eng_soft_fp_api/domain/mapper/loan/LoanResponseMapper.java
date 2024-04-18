package com.github.faening.eng_soft_fp_api.domain.mapper.loan;

import com.github.faening.eng_soft_fp_api.data.model.Loan;
import com.github.faening.eng_soft_fp_api.domain.mapper.AbstractMapper;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Service
public class LoanResponseMapper extends AbstractMapper<Loan, LoanResponseDTO> {
    @Autowired
    public LoanResponseMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void createSourceToDestinationMapping() {
        modelMapper.createTypeMap(Loan.class, LoanResponseDTO.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(Loan::getId, LoanResponseDTO::setId);
                mapper.when(notNull).map(src -> src.getEmployee().getId(), LoanResponseDTO::setEmployeeId);
                mapper.when(notNull).map(Loan::getLoanAmountValue, LoanResponseDTO::setLoanAmountValue);
                mapper.when(notNull).map(Loan::getInstallmentQuantity, LoanResponseDTO::setInstallmentQuantity);
                mapper.when(notNull).map(Loan::getRequestDate, LoanResponseDTO::setRequestDate);
                mapper.when(notNull).map(Loan::getApprovalDate, LoanResponseDTO::setApprovalDate);
                mapper.when(notNull).map(Loan::getCompanyPaymentDate, LoanResponseDTO::setCompanyPaymentDate);
                mapper.when(notNull).map(Loan::getCompanyPaymentStatus, LoanResponseDTO::setCompanyPaymentStatus);
                mapper.when(notNull).map(Loan::getEmployeePaymentStatus, LoanResponseDTO::setEmployeePaymentStatus);
                mapper.when(notNull).map(Loan::getInstallments, LoanResponseDTO::setInstallments);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getCreatedAt(), LoanResponseDTO::setCreatedAt);
                mapper.when(notNull).map(src -> src.getEntityMetadata().getUpdatedAt(), LoanResponseDTO::setUpdatedAt);
            });
    }

    @Override
    protected void createDestinationToSourceMapping() {
        modelMapper.createTypeMap(LoanResponseDTO.class, Loan.class)
            .addMappings(mapper -> {
                mapper.when(notNull).map(LoanResponseDTO::getId, Loan::setId);
                mapper.when(notNull).<Integer>map(LoanResponseDTO::getEmployeeId, (dest, v) -> dest.getEmployee().setId(v));
                mapper.when(notNull).map(LoanResponseDTO::getLoanAmountValue, Loan::setLoanAmountValue);
                mapper.when(notNull).map(LoanResponseDTO::getInstallmentQuantity, Loan::setInstallmentQuantity);
                mapper.when(notNull).map(LoanResponseDTO::getRequestDate, Loan::setRequestDate);
                mapper.when(notNull).map(LoanResponseDTO::getApprovalDate, Loan::setApprovalDate);
                mapper.when(notNull).map(LoanResponseDTO::getCompanyPaymentDate, Loan::setCompanyPaymentDate);
                mapper.when(notNull).map(LoanResponseDTO::getCompanyPaymentStatus, Loan::setCompanyPaymentStatus);
                mapper.when(notNull).map(LoanResponseDTO::getEmployeePaymentStatus, Loan::setEmployeePaymentStatus);
                mapper.when(notNull).map(LoanResponseDTO::getInstallments, Loan::setInstallments);
                mapper.when(notNull).<LocalDateTime>map(LoanResponseDTO::getCreatedAt, (dest, v) -> dest.getEntityMetadata().setCreatedAt(v));
                mapper.when(notNull).<LocalDateTime>map(LoanResponseDTO::getUpdatedAt, (dest, v) -> dest.getEntityMetadata().setUpdatedAt(v));
            });
    }
}