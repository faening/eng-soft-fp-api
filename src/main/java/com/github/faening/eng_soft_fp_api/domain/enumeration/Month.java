package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum Month {
    JANUARY("month.january"),
    FEBRUARY("month.february"),
    MARCH("month.march"),
    APRIL("month.april"),
    MAY("month.may"),
    JUNE("month.june"),
    JULY("month.july"),
    AUGUST("month.august"),
    SEPTEMBER("month.september"),
    OCTOBER("month.october"),
    NOVEMBER("month.november"),
    DECEMBER("month.december");

    private final String code;
    private static MessageSource messageSource;

    Month(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        Month.messageSource = messageSource;
    }
}