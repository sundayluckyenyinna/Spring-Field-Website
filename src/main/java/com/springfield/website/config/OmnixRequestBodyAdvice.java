package com.springfield.website.config;

import com.springfield.website.config.logger.OmnixHttpLogger;
import com.springfield.website.instrumentation.CustomHttpInputMessage;
import com.springfield.website.utils.StringValues;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = { "com.springfield.website" })
public class OmnixRequestBodyAdvice implements RequestBodyAdvice {

    private final OmnixHttpLogger logger;
    private final HttpServletRequest servletRequest;

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(@NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        String requestBody;
        try {
            byte[] bytes = StreamUtils.copyToByteArray(inputMessage.getBody());
            requestBody = new String(bytes);
        }catch (Exception exception){
            log.error("Exception occurred while trying to get request body from InputStream");
            requestBody = StringValues.EMPTY_STRING;
        }
        servletRequest.setAttribute(StringValues.REQUEST_BODY_KEY, requestBody);
        return new CustomHttpInputMessage(new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8)), inputMessage.getHeaders());
    }

    @Override
    public Object afterBodyRead(@NonNull Object body, @NonNull HttpInputMessage inputMessage, @NonNull MethodParameter parameter, @NonNull Type targetType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        logger.logHttpApiRequest(body, servletRequest);
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        logger.logHttpApiRequest(body, servletRequest);
        return body;
    }
}
