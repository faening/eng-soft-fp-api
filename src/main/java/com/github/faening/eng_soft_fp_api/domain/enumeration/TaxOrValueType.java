package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public enum TaxOrValueType {
    MINIMUM_WAGE("taxOrValueType.minimumWage"),

    // Benefits in R$
    DAYCARE_ALLOWANCE("taxOrValueType.daycareAllowance"),
    TRANSPORT_ALLOWANCE("taxOrValueType.transportAllowance"),
    MEAL_ALLOWANCE("taxOrValueType.mealAllowance"),

    // Benefits in %
    SALES_ALLOWANCE("taxOrValueType.salesAllowance"),
    NIGHT_SHIFT_ALLOWANCE("taxOrValueType.nightShiftAllowance"),
    UNHEALTHINESS_ALLOWANCE("taxOrValueType.unhealthinessAllowance"),
    DANGEROUSNESS_ALLOWANCE("taxOrValueType.dangerousnessAllowance"),
    FAMILY_ALLOWANCE("taxOrValueType.familyAllowance"),
    TIME_SERVICE_ALLOWANCE("taxOrValueType.timeServiceAllowance"),

    // Taxes %
    INSS("taxOrValueType.inss"),
    IRRF("taxOrValueType.irrf"),
    FGTS("taxOrValueType.fgts"),
    TRANSPORT_DISCOUNT("taxOrValueType.transportDiscount"),
    MEAL_DISCOUNT("taxOrValueType.mealDiscount");

    private final String code;
    private static MessageSource messageSource;

    TaxOrValueType(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        TaxOrValueType.messageSource = messageSource;
    }
}