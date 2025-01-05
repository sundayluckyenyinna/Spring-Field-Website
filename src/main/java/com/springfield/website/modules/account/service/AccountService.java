package com.springfield.website.modules.account.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.modules.account.payload.AccountRequestPayload;

public interface AccountService {
    OmnixApiResponse<String> processAccountOpening(RequestMeta requestMeta, AccountRequestPayload requestPayload);
}
