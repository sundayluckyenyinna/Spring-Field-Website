package com.springfield.website.modules.loan.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.ParamKey;
import com.springfield.website.modules.loan.model.BusinessLocation;
import com.springfield.website.modules.loan.model.BusinessType;
import com.springfield.website.modules.loan.model.CollateralType;
import com.springfield.website.modules.loan.model.LoanApplication;
import com.springfield.website.modules.loan.payload.*;
import com.springfield.website.modules.loan.repository.LoanApplicationRepository;
import com.springfield.website.modules.param.LocalParamSource;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService{

    private final LocalParamSource localParamSource;
    private final LoanValidationService validationService;
    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public OmnixApiResponse<CreditBreakdownResponse> processLoanCalculationCreditBreakdown(CreditBreakdownRequest creditBreakdownRequest){
        try{
            CreditBreakdownResponse response = CreditCalculator.calculateCreditBreakdown(creditBreakdownRequest);
            return OmnixApiResponse.ofSuccessful(response);
        }catch (Exception exception){
            throw HttpUtil.getResolvedException(exception);
        }
    }

    @Override
    public OmnixApiResponse<LoanApplicationResponseData> processLoanApplication(LoanApplicationRequestPayload requestPayload){
        try{
            validationService.validateOngoingLoanUniquenessByPhoneNumberAnd(requestPayload.getPhoneNumber());
            validationService.validateAccountExistenceForLoan(requestPayload.getPhoneNumber());
            int tenure = Integer.parseInt(localParamSource.getParamOrDefault(ParamKey.DEFAULT_BUSINESS_LOAN_APPLICATION_TENURE, "4"));
            double businessLoanInterestRate = Double.parseDouble(localParamSource.getParamOrDefault(ParamKey.BUSINESS_LOAN_APPLICATION_INTEREST, "5"));
            int businessLoanInterestIncrement = Integer.parseInt(localParamSource.getParamOrDefault(ParamKey.DEFAULT_BUSINESS_LOAN_APPLICATION_INTEREST_INCREMENT, "0"));

            CreditBreakdownRequest creditBreakdownRequest = CreditBreakdownRequest.builder()
                    .totalAmount(requestPayload.getLoanAmount())
                    .tenure(tenure)
                    .interestRate(businessLoanInterestRate)
                    .interestIncrement(businessLoanInterestIncrement)
                    .build();
            CreditBreakdownResponse creditBreakdownResponse = CreditCalculator.calculateCreditBreakdown(creditBreakdownRequest);
            LoanApplication loanApplication = LoanApplication.builder()
                    .guid(CommonUtil.generateGuid())
                    .fullName(requestPayload.getFullName())
                    .gender(requestPayload.getGender())
                    .dateOfBirth(LocalDate.parse(requestPayload.getDateOfBirth()))
                    .email(requestPayload.getEmail())
                    .phoneNumber(requestPayload.getPhoneNumber())
                    .stateOfResidence(requestPayload.getStateOfResidence())
                    .contactAddress(requestPayload.getContactAddress())
                    .loanAmount(requestPayload.getLoanAmount())
                    .loanPurpose(requestPayload.getLoanPurpose())
                    .loanTenure(requestPayload.getLoanTenure())
                    .build();
            LoanApplication createdLoanApplication = loanApplicationRepository.saveAndFlush(loanApplication);
            log.info("Loan Application created successfully with ID: {}", createdLoanApplication.getId());
            LoanApplicationResponseData responseData = LoanApplicationResponseData.builder()
                    .email(requestPayload.getEmail())
                    .phoneNumber(requestPayload.getPhoneNumber())
                    .approvedAmount(creditBreakdownResponse.getTotalAmount())
                    .totalLoanRepayment(creditBreakdownResponse.getTotalRepayment())
                    .build();
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception exception){
            throw HttpUtil.getResolvedException(exception);
        }
    }

    @Override
    public OmnixApiResponse<List<BusinessTypeResponseData>> processFetchBusinessTypes(){
        try{
            final AtomicInteger counter = new AtomicInteger(1);
            List<BusinessTypeResponseData> responseData = Arrays.stream(BusinessType.values())
                    .map(businessType -> BusinessTypeResponseData.fromIdAndBusinessType(counter.getAndIncrement(), businessType))
                    .toList();
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            throw HttpUtil.getResolvedException(e);
        }
    }

    @Override
    public OmnixApiResponse<List<CollateralTypeResponseData>> processFetchCollateralTypes(){
        try{
            final AtomicInteger counter = new AtomicInteger(1);
            List<CollateralTypeResponseData> responseData = Arrays.stream(CollateralType.values())
                    .map(collateralType -> CollateralTypeResponseData.fromIdAndCollateralType(counter.getAndIncrement(), collateralType))
                    .toList();
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            throw HttpUtil.getResolvedException(e);
        }
    }

    @Override
    public OmnixApiResponse<List<BusinessLocationResponseData>> processFetchBusinessLocation(){
        try{
            final AtomicInteger counter = new AtomicInteger(1);
            List<BusinessLocationResponseData> responseData = Arrays.stream(BusinessLocation.values())
                    .map(businessLocation -> BusinessLocationResponseData.fromIdAndBusinessLocation(counter.getAndIncrement(), businessLocation))
                    .toList();
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            throw HttpUtil.getResolvedException(e);
        }
    }
}
