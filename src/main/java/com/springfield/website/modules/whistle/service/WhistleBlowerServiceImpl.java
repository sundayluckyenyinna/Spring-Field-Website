package com.springfield.website.modules.whistle.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.modules.whistle.model.ContactUs;
import com.springfield.website.modules.whistle.model.WhistleBlower;
import com.springfield.website.modules.whistle.payload.ContactUsRequestPayload;
import com.springfield.website.modules.whistle.payload.WhistleBlowerRequestPayload;
import com.springfield.website.modules.whistle.repository.ContactUsRepository;
import com.springfield.website.modules.whistle.repository.WhistleBlowerRepository;
import com.springfield.website.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WhistleBlowerServiceImpl implements WhistleBlowerService{

    private final ContactUsRepository contactUsRepository;
    private final WhistleBlowerRepository whistleBlowerRepository;


    @Override
    public OmnixApiResponse<String> processWhistleBlowerSubmission(RequestMeta requestMeta, WhistleBlowerRequestPayload requestPayload){
        try{
            WhistleBlower whistleBlower = WhistleBlower.fromRequest(requestPayload);
            WhistleBlower createdBlower = whistleBlowerRepository.saveAndFlush(whistleBlower);
            log.info("Created Whistle blower with ID: {}", createdBlower.getId());
            OmnixApiResponse<String> response = new OmnixApiResponse<>();
            response.setResponseCode(ResponseCode.SUCCESS_CODE);
            response.setResponseMessage("Enquiry submitted successfully");
            response.setResponseData("Enquiry submitted successfully");
            return response;
        }catch (Exception exception){
            exception.printStackTrace();
            throw HttpUtil.getResolvedException(ResponseCode.FAILED_TRANSACTION, exception.getMessage());
        }
    }

    @Override
    public OmnixApiResponse<String> processContactUsSubmission(RequestMeta requestMeta, ContactUsRequestPayload requestPayload){
        try{
            ContactUs contactUs = ContactUs.fromRequest(requestPayload);
            ContactUs createdContactUs = contactUsRepository.saveAndFlush(contactUs);
            log.info("ContactUs created successfully with ID: {}", createdContactUs.getId());
            return OmnixApiResponse.ofSuccessful("Contact request siubmitted successfully");
        }catch (Exception exception){
            throw HttpUtil.getResolvedException(exception);
        }
    }
}
