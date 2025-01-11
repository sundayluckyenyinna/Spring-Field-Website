package com.springfield.website.modules.generic.email;


import com.springfield.website.modules.appuser.entities.AppUser;
import com.springfield.website.modules.generic.email.payload.EmailResponseData;
import com.springfield.website.modules.generic.email.payload.SimpleEmailRequestPayload;

import java.util.List;
import java.util.Map;

public interface EmailService {
    EmailResponseData processSendSimpleEmail(SimpleEmailRequestPayload requestPayload);

    void scheduleEmail(AppUser appUser, String subject, String template, Map<String, Object> context, List<String> recipients);
}
