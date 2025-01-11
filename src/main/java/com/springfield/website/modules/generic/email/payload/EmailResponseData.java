package com.springfield.website.modules.generic.email.payload;

import lombok.Data;

@Data
public class EmailResponseData {
    private boolean success = false;
    private String emailSendResponseId;

    public static EmailResponseData withSenderId(String senderId){
        EmailResponseData responseData = new EmailResponseData();
        responseData.setSuccess(true);
        responseData.setEmailSendResponseId(senderId);
        return responseData;
    }

    public static EmailResponseData withFailure(Exception exception){
        EmailResponseData responseData = new EmailResponseData();
        responseData.setSuccess(false);
        responseData.setEmailSendResponseId(exception.getMessage());
        return responseData;
    }
}
