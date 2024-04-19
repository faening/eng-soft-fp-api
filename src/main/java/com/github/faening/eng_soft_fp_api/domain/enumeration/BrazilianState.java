package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum BrazilianState {
    AC("brazilianState.AC"),
    AL("brazilianState.AL"),
    AP("brazilianState.AP"),
    AM("brazilianState.AM"),
    BA("brazilianState.BA"),
    CE("brazilianState.CE"),
    DF("brazilianState.DF"),
    ES("brazilianState.ES"),
    GO("brazilianState.GO"),
    MA("brazilianState.MA"),
    MT("brazilianState.MT"),
    MS("brazilianState.MS"),
    MG("brazilianState.MG"),
    PA("brazilianState.PA"),
    PB("brazilianState.PB"),
    PR("brazilianState.PR"),
    PE("brazilianState.PE"),
    PI("brazilianState.PI"),
    RJ("brazilianState.RJ"),
    RN("brazilianState.RN"),
    RS("brazilianState.RS"),
    RO("brazilianState.RO"),
    RR("brazilianState.RR"),
    SC("brazilianState.SC"),
    SP("brazilianState.SP"),
    SE("brazilianState.SE"),
    TO("brazilianState.TO");

    private final String code;
    private static MessageSource messageSource;

    BrazilianState(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        BrazilianState.messageSource = messageSource;
    }
}