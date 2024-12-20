package com.springfield.website.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springfield.website.common.ApiBaseResponse;
import com.springfield.website.common.OmnixApiException;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.config.logger.OmnixHttpLogger;
import com.springfield.website.utils.StringValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.springfield.website")
public class OmnixRestControllerAdvice {

    private final OmnixHttpLogger logger;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = OmnixApiException.class)
    public void onOmnixApiException(OmnixApiException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(exception.getCode());
        apiBaseResponse.setResponseMessage(exception.getMessage());
        apiBaseResponse.setErrors(exception.getErrors());
        writeResponseToClient(apiBaseResponse, exception.getStatusCode(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void onMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        List<ObjectError> objectErrors = exception.getBindingResult().getAllErrors();
        List<String> errors = objectErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.FAILED_MODEL);
        apiBaseResponse.setResponseMessage(String.join(StringValues.COMMA, errors));
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, HttpStatus.BAD_REQUEST.value(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public void onConstraintViolationException(ConstraintViolationException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        List<String> errors = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.FAILED_MODEL);
        apiBaseResponse.setResponseMessage(String.join(StringValues.COMMA, errors));
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, HttpStatus.BAD_REQUEST.value(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = ValidationException.class)
    public void onValidationException(ValidationException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.FAILED_MODEL);
        apiBaseResponse.setResponseMessage(exception.getMessage());
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, HttpStatus.BAD_REQUEST.value(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = ResponseStatusException.class)
    public void onResponseStatusException(ResponseStatusException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.HTTP_ERROR);
        apiBaseResponse.setResponseMessage(exception.getMessage());
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, exception.getStatusCode().value(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public void onMissingServletRequestParameterException(MissingServletRequestParameterException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.FAILED_MODEL);
        apiBaseResponse.setResponseMessage(exception.getMessage());
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, HttpStatus.BAD_REQUEST.value(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public void onHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.FAILED_MODEL);
        apiBaseResponse.setResponseMessage(exception.getMessage());
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, HttpStatus.BAD_REQUEST.value(), servletRequest, servletResponse);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public void onGenericRuntimeException(RuntimeException exception, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        exception.printStackTrace();
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(ResponseCode.FAILED_MODEL);
        apiBaseResponse.setResponseMessage(exception.getMessage());
        apiBaseResponse.setErrors(Collections.singletonList(apiBaseResponse.getResponseMessage()));
        writeResponseToClient(apiBaseResponse, HttpStatus.OK.value(), servletRequest, servletResponse);
    }

    private void writeResponseToClient(Object payload, int statusCode, HttpServletRequest servletRequest,  HttpServletResponse servletResponse) throws Exception{
        logger.logHttpApiResponse(payload, statusCode, servletResponse);
//        servletResponse.setStatus(statusCode);
//        servletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        servletResponse.setHeader("X-FORWARDED-FOR", "BPAY-DIGITAL");
//        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        servletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        servletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, idToken");
//        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        servletResponse.setHeader("Access-Control-Max-Age", "31536000");
        String responseJson = objectMapper.writeValueAsString(payload);
        servletResponse.getWriter().write(responseJson);
    }
}
