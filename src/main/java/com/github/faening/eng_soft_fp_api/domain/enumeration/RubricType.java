package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum RubricType {
    INCOME("rubricType.income"),
    DEDUCTION("rubricType.deduction"),
    NEUTRAL("rubricType.neutral");

    private final String code;
    private static MessageSource messageSource;

    RubricType(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        RubricType.messageSource = messageSource;
    }
}