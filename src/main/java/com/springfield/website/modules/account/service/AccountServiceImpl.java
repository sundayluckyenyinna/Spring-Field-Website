package com.springfield.website.modules.account.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.modules.account.entities.AccountRequest;
import com.springfield.website.modules.account.payload.AccountRequestPayload;
import com.springfield.website.modules.account.repository.AccountRequestRepository;
import com.springfield.website.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRequestRepository accountRequestRepository;


    @Override
    public OmnixApiResponse<String> processAccountOpening(RequestMeta requestMeta, AccountRequestPayload requestPayload){
        try{
            AccountRequest accountRequest = AccountRequest.fromRequest(requestPayload);
            AccountRequest createdAccountRequest = accountRequestRepository.saveAndFlush(accountRequest);
            log.info("Account request created successfully with ID: {}", createdAccountRequest.getId());
            OmnixApiResponse<String> response = OmnixApiResponse.getInstance();
            response.setResponseCode(ResponseCode.SUCCESS_CODE);
            response.setResponseMessage("Account request created successfully");
            response.setResponseData("Account request created successfully");
            return response;
        }catch (Exception exception){
            throw HttpUtil.getResolvedException(ResponseCode.FAILED_TRANSACTION, exception.getMessage());
        }
    }
}
