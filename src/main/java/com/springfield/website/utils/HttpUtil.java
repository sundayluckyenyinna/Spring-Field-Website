package com.springfield.website.utils;

import com.springfield.website.common.OmnixApiException;
import com.springfield.website.common.ResponseCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HttpUtil {

    public static OmnixApiException getResolvedException(String responseCode, String responseMessage){
        return OmnixApiException.newInstance()
                .withCode(responseCode)
                .withMessage(responseMessage)
                .withError(responseMessage)
                .withErrors(new ArrayList<>(List.of(responseMessage)));
    }

    public static OmnixApiException getResolvedException(String responseCode, Exception e){
        return OmnixApiException.newInstance()
                .withCode(responseCode)
                .withMessage(e.getMessage())
                .withError(e.getMessage())
                .withException(e)
                .withErrors(new ArrayList<>(List.of(e.getMessage())));
    }

    public static OmnixApiException getResolvedException(Exception e){
        e.printStackTrace();
        return OmnixApiException.newInstance()
                .withCode(ResponseCode.INTERNAL_SERVER_ERROR)
                .withMessage(e.getMessage())
                .withError(e.getMessage())
                .withException(e)
                .withErrors(new ArrayList<>(List.of(e.getMessage())));
    }
}
