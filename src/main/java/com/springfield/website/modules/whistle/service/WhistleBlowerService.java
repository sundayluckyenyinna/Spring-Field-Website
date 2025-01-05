package com.springfield.website.modules.whistle.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.modules.whistle.payload.ContactUsRequestPayload;
import com.springfield.website.modules.whistle.payload.WhistleBlowerRequestPayload;

public interface WhistleBlowerService {
    OmnixApiResponse<String> processWhistleBlowerSubmission(RequestMeta requestMeta, WhistleBlowerRequestPayload requestPayload);

    OmnixApiResponse<String> processContactUsSubmission(RequestMeta requestMeta, ContactUsRequestPayload requestPayload);
}
