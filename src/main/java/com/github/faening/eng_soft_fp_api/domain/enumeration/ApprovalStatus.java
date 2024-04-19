package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum ApprovalStatus {
    PENDING("approvalStatus.pending"),
    APPROVED("approvalStatus.approved"),
    DENIED("approvalStatus.denied");

    private final String code;
    private static MessageSource messageSource;

    ApprovalStatus(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        ApprovalStatus.messageSource = messageSource;
    }
}