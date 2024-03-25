package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum ExperienceLevel {
    TRAINEE("experienceLevel.trainee"),
    ENTRY_LEVEL("experienceLevel.entryLevel"),
    JUNIOR("experienceLevel.junior"),
    MID_LEVEL("experienceLevel.midLevel"),
    SENIOR("experienceLevel.senior"),
    EXPERT("experienceLevel.expert"),;

    private final String code;
    private static MessageSource messageSource;

    ExperienceLevel(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        ExperienceLevel.messageSource = messageSource;
    }
}