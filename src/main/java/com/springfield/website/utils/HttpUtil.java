package com.springfield.website.utils;

import com.springfield.website.common.OmnixApiException;
import com.springfield.website.common.ResponseCode;

import java.util.Collections;

public class HttpUtil {

    public static OmnixApiException getResolvedException(String responseCode, String responseMessage){
        return OmnixApiException.newInstance()
                .withCode(responseCode)
                .withMessage(responseMessage)
                .withError(responseMessage)
                .withErrors(Collections.singletonList(responseMessage));
    }

    public static OmnixApiException getResolvedException(String responseCode, Exception e){
        return OmnixApiException.newInstance()
                .withCode(responseCode)
                .withMessage(e.getMessage())
                .withError(e.getMessage())
                .withException(e)
                .withErrors(Collections.singletonList(e.getMessage()));
    }

    public static OmnixApiException getResolvedException(Exception e){
        return OmnixApiException.newInstance()
                .withCode(ResponseCode.INTERNAL_SERVER_ERROR)
                .withMessage(e.getMessage())
                .withError(e.getMessage())
                .withException(e)
                .withErrors(Collections.singletonList(e.getMessage()));
    }
}
