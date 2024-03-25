package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum AbsenceType {
    SICK_LEAVE("absenceType.sickLeave"),
    VACATION("absenceType.vacation"),
    DAY_OFF("absenceType.dayOff"),
    MATERNITY_LEAVE("absenceType.maternityLeave"),
    PATERNITY_LEAVE("absenceType.paternityLeave"),
    ABSENCE_WITHOUT_JUSTIFICATION("absenceType.absenceWithoutJustification");

    private final String code;
    private static MessageSource messageSource;

    AbsenceType(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        AbsenceType.messageSource = messageSource;
    }
}