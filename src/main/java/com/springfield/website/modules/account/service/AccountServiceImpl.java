package com.springfield.website.modules.account.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.ParamKey;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.modules.account.entities.AccountRequest;
import com.springfield.website.modules.account.payload.AccountRequestPayload;
import com.springfield.website.modules.account.repository.AccountRequestRepository;
import com.springfield.website.modules.generic.email.EmailService;
import com.springfield.website.modules.param.LocalParamSource;
import com.springfield.website.utils.HttpUtil;
import com.springfield.website.utils.MapBuilder;
import com.springfield.website.utils.StringValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final EmailService emailService;
    private final LocalParamSource localParamSource;
    private final AccountRequestRepository accountRequestRepository;


    @Override
    public OmnixApiResponse<String> processAccountOpening(RequestMeta requestMeta, AccountRequestPayload requestPayload){
        try{
            AccountRequest accountRequest = AccountRequest.fromRequest(requestPayload);
            AccountRequest createdAccountRequest = accountRequestRepository.saveAndFlush(accountRequest);

            Map<String, Object> context = MapBuilder.start()
                    .add("customerName", accountRequest.getFullName())
                    .add("gender", accountRequest.getGender())
                    .add("email", accountRequest.getEmailAddress())
                    .add("dob", accountRequest.getDateOfBirth().toString())
                    .add("bvn", accountRequest.getBvn())
                    .add("nin", accountRequest.getNin())
                    .add("address", accountRequest.getContactAddress())
                    .add("state", accountRequest.getStateOfResidence())
                    .add("phoneNumber", accountRequest.getPhoneNumber())
                    .add("meansOfId", accountRequest.getMeansOfIdentification())
                    .add("accountType", accountRequest.getProductType())
                    .add("billUrl", accountRequest.getUtilityBillUrl())
                    .add("documentUrl", accountRequest.getDocumentUrl())
                    .add("submissionDate", accountRequest.getCreatedAt().toString())
                    .asMap();
            String adminRecipients = localParamSource.getParamOrDefault(ParamKey.ADMIN_RECIPIENT_MAILS, "sundayluckyenyinnadeveloper@gmail.com, support@springfieldmfb.com");
            List<String> adminEmails = Arrays.stream(adminRecipients.split(StringValues.COMMA)).toList();
            emailService.scheduleEmail(requestMeta.getAppUser(), "Notification of Account Opening Request via Website", "account-opening.html", context, adminEmails);
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
