package com.github.faening.eng_soft_fp_api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@SuppressWarnings("unused")
@Service
public abstract class AbstractService<REQ, RES> {
    @Autowired
    protected MessageSource messageSource;

    public String getLocalizedMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, null, locale);
    }

    public abstract List<RES> getAll();

    public abstract RES getById(Integer id);

    public abstract RES create(REQ request);

    public abstract RES update(Integer id, REQ request);

    public abstract void delete(Integer id);
}