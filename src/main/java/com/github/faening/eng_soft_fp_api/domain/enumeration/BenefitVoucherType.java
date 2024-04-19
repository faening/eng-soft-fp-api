package com.github.faening.eng_soft_fp_api.domain.enumeration;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SuppressWarnings("unused")
public enum BenefitVoucherType {
    MEAL("benefitVoucherType.meal"),
    TRANSPORT("benefitVoucherType.transport");

    private final String code;
    private static MessageSource messageSource;

    BenefitVoucherType(String code) {
        this.code = code;
    }

    public String getDescription() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public static void setMessageSource(MessageSource messageSource) {
        BenefitVoucherType.messageSource = messageSource;
    }
}
