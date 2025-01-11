package com.springfield.website.modules.generic.email.factory;

import com.springfield.website.common.OtpAction;
import com.springfield.website.modules.appuser.entities.AppUser;
import com.springfield.website.modules.generic.email.EmailService;
import com.springfield.website.utils.MapBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class EmailVerificationMessageFactory implements EmailMessageFactory{

    private final EmailService emailService;

    @Override
    public void sendContextualNotification(AppUser appUser, String recipientEmail, String verificationCode) {
        Map<String, Object> context = MapBuilder.start()
                .add("email", recipientEmail)
                .add("verificationCode", verificationCode)
                .asMap();
        emailService.scheduleEmail(appUser, "Email verification", "user_email_verification.html", context, Collections.singletonList(recipientEmail));
    }

    @Override
    public boolean supportsAction(OtpAction action) {
        return action == OtpAction.SIGNUP_EMAIL_VERIFICATION;
    }
}
