package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum PayrollStatus {
    PENDING("payrollStatus.pending"),
    RELEASED("payrollStatus.released"),
    APPROVED("payrollStatus.approved"),
    PAID("payrollStatus.paid");

    private final String code;
    private static MessageSource messageSource;

    PayrollStatus(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        PayrollStatus.messageSource = messageSource;
    }
}
