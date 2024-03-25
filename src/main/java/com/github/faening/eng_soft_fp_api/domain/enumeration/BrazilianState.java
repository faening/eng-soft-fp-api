package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum BrazilianState {
    AC("brazilianState.ac"),
    AL("brazilianState.al"),
    AP("brazilianState.ap"),
    AM("brazilianState.am"),
    BA("brazilianState.ba"),
    CE("brazilianState.ce"),
    DF("brazilianState.df"),
    ES("brazilianState.es"),
    GO("brazilianState.go"),
    MA("brazilianState.ma"),
    MT("brazilianState.mt"),
    MS("brazilianState.ms"),
    MG("brazilianState.mg"),
    PA("brazilianState.pa"),
    PB("brazilianState.pb"),
    PR("brazilianState.pr"),
    PE("brazilianState.pe"),
    PI("brazilianState.pi"),
    RJ("brazilianState.rj"),
    RN("brazilianState.rn"),
    RS("brazilianState.rs"),
    RO("brazilianState.ro"),
    RR("brazilianState.rr"),
    SC("brazilianState.sc"),
    SP("brazilianState.sp"),
    SE("brazilianState.se"),
    TO("brazilianState.to");

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