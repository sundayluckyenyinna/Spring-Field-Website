package com.springfield.website.modules.generic.email.factory;


import com.springfield.website.common.OtpAction;
import com.springfield.website.modules.appuser.entities.AppUser;

public interface EmailMessageFactory {

    void sendContextualNotification(AppUser appUser, String recipientEmail, String verificationCode);
    boolean supportsAction(OtpAction action);
}
