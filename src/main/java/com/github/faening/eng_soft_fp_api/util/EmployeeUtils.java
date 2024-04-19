package com.github.faening.eng_soft_fp_api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("SpellCheckingInspection")
public class EmployeeUtils {
    /**
     * Retorna o valor da hora de um funcionário
     * @param salary Salário do funcionário
     * @param totalHours Total de horas trabalhadas
     * @return Valor da hora
     */
    public static BigDecimal calculateHourlyRate(BigDecimal salary, int totalHours) {
        return salary.divide(BigDecimal.valueOf(totalHours), 2, RoundingMode.HALF_UP);
    }
}