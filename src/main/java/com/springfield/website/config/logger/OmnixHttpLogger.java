package com.springfield.website.config.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springfield.website.common.LogStyle;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.StringValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Objects;


@Slf4j
@Configuration

@RequiredArgsConstructor
@EnableConfigurationProperties(value = { LoggerStyleProperties.class })
public class OmnixHttpLogger implements Loggable{

    private final LoggerStyleProperties loggerStyleProperties;
    private final ObjectMapper objectMapper;

    public void logHttpApiRequest(Object requestBody, HttpServletRequest servletRequest){
        try {
            System.out.println();
            log.info("=============================================  HTTP REQUEST START ======================================================");
            log.info("Request URI: {} {}", servletRequest.getMethod(), servletRequest.getRequestURI());
            log.info("Remote IP: {}", CommonUtil.returnOrDefault(servletRequest.getHeader("X-FORWARDED-FOR"), servletRequest.getRemoteAddr()));
            log.info("Request SessionId: {}", servletRequest.getRequestedSessionId());
            writeBodyByLogStyle(requestBody);
            log.info("Request Headers: {}", getHeadersFromServletRequest(servletRequest));
            log.info("Request Params: {}", getTypesafeRequestMap(servletRequest));
            log.info("Request Protocol: {}", servletRequest.getProtocol());
            log.info("Request Scheme: {}", servletRequest.getScheme());
            log.info("-----------------------------------------------------------------------------------------------------------------------");
        } catch (Exception ignored) {
        }
    }

    public void logHttpApiResponse(Object responseBody, HttpServletResponse servletResponse){
        try {
            System.out.println();
            log.info("=============================================  HTTP RESPONSE END ======================================================");
            log.info("Response Status: {}", HttpStatus.resolve(servletResponse.getStatus()));
            writeBodyByLogStyle(responseBody);
            log.info("Response Headers: {}", getHeadersFromServletResponse(servletResponse));
            log.info("=======================================================================================================================");
            System.out.println();
        }catch (Exception ignored){}
    }

    public void logHttpApiResponse(Object responseBody, int status, HttpServletResponse servletResponse){
        try {
            log.info("");
            log.info("=============================================  HTTP RESPONSE END ======================================================");
            log.info("Response Status: {}", HttpStatus.resolve(status));
            writeBodyByLogStyle(responseBody);
            log.info("Response Headers: {}", getHeadersFromServletResponse(servletResponse));
            log.info("=======================================================================================================================");
        }catch (Exception ignored){}
    }

    private void writeBodyByLogStyle(Object body) throws JsonProcessingException {
        if(getLogStyle(loggerStyleProperties.getLogStyle()) == LogStyle.PRETTY_PRINT) {
            log.info("Body: {}", Objects.isNull(body) ? StringValues.EMPTY_STRING : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body));
        }else{
            log.info("Body: {}", Objects.isNull(body) ? StringValues.EMPTY_STRING : (body instanceof String ? (String) body : objectMapper.writeValueAsString(body)));
        }
    }

    private LogStyle getLogStyle(String logStyle){
        try{
            return LogStyle.valueOf(logStyle.toUpperCase());
        }catch (Exception exception){
            return LogStyle.DEFAULT;
        }
    }
}
