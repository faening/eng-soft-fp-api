package com.github.faening.eng_soft_fp_api.domain.calculation.deduction;

import com.github.faening.eng_soft_fp_api.domain.calculation.CalculationParameters;
import com.github.faening.eng_soft_fp_api.domain.calculation.PayrollCalculation;
import com.github.faening.eng_soft_fp_api.domain.enumeration.PaymentStatus;
import com.github.faening.eng_soft_fp_api.domain.model.loan.LoanResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.loan_installment.LoanInstallmentResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.model.payroll_item.PayrollItemRequestDTO;
import com.github.faening.eng_soft_fp_api.domain.model.rubric.RubricResponseDTO;
import com.github.faening.eng_soft_fp_api.domain.service.LoanService;
import com.github.faening.eng_soft_fp_api.domain.service.RubricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
@Component
public class CalculateLoanDiscount implements PayrollCalculation {
    private final RubricService rubricService;
    private final LoanService loanService;
    private final static Integer RUBRIC_CODE = 9200;

    @Autowired
    public CalculateLoanDiscount(
        RubricService rubricService,
        LoanService loanService
    ) {
        this.rubricService = rubricService;
        this.loanService = loanService;
    }

    @Override
    public PayrollItemRequestDTO calculate(CalculationParameters parameters) {
        return Optional.ofNullable(parameters)
            .map(params -> {
                BigDecimal loanInstallments = getLoanInstallments(params);
                return loanInstallments.compareTo(BigDecimal.ZERO) > 0 ?
                    new PayrollItemRequestDTO(
                        getRubricByCode(),
                        null,
                        loanInstallments,
                        loanInstallments,
                        BigDecimal.ZERO
                    ) : null;
            })
            .orElse(null);
    }

    /**
     * Este método recupera uma rubrica pelo seu código.
     *
     * @return Um objeto RubricResponseDTO que representa a rubrica recuperada.
     */
    public RubricResponseDTO getRubricByCode() {
        return rubricService.getByCode(RUBRIC_CODE);
    }

    /**
     * Este método calcula o valor total das parcelas de empréstimo pendentes de um funcionário em um determinado mês.
     *
     * @param parameters Um objeto CalculationParameters que contém os parâmetros necessários para o cálculo.
     * @return Um BigDecimal que representa o valor total das parcelas de empréstimo pendentes.
     */
    protected BigDecimal getLoanInstallments(CalculationParameters parameters) {
        if (parameters == null) {
            return null;
        }

        // Recupera os empréstimos do funcionário com parcelas pendentes no mês especificado
        List<LoanResponseDTO> loans = loanService.getLoanByEmployeeIdAndSpecs(
            parameters.getEmployee().getId(),
            null,
            null,
            null,
            PaymentStatus.PAID,
            PaymentStatus.RELEASED
        );

        // Calcula o valor total das parcelas de empréstimo pendentes no mês especificado.
        // Se houver parcelas pendentes, retorna a soma dos valores das parcelas pendentes. Caso contrário, retorna zero.
        return Optional.ofNullable(loans)
            .orElse(Collections.emptyList())
            .stream()
            .flatMap(loan -> Optional.ofNullable(loan.getInstallments()).orElse(Collections.emptyList()).stream())
            .filter(installment -> installment.getDiscountMonth().equals(parameters.getMonth())
                 && installment.getPaymentStatus().equals(PaymentStatus.PENDING))
            .map(LoanInstallmentResponseDTO::getInstallmentValue)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }
}