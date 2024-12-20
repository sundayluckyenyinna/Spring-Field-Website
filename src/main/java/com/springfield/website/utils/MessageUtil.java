package com.springfield.website.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageUtil {

    private final MessageSource messageSource;

    public String getMessage(String code){
        return messageSource.getMessage(code, new Object[]{}, code, Locale.ENGLISH);
    }

    public String getMessage(String code, Object[] params){
        return messageSource.getMessage(code, params, code, Locale.ENGLISH);
    }
}
