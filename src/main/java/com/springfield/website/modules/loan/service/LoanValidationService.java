package com.springfield.website.modules.loan.service;

import com.springfield.website.common.ResponseCode;
import com.springfield.website.modules.account.entities.AccountRequest;
import com.springfield.website.modules.account.repository.AccountRequestRepository;
import com.springfield.website.modules.loan.model.LoanApplication;
import com.springfield.website.modules.loan.model.LoanApplicationStatus;
import com.springfield.website.modules.loan.repository.LoanApplicationRepository;
import com.springfield.website.utils.HttpUtil;
import com.springfield.website.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanValidationService {

    private final MessageUtil messageUtil;
    private final AccountRequestRepository accountRequestRepository;
    private final LoanApplicationRepository loanApplicationRepository;

    public void validateOngoingLoanUniquenessByPhoneNumberAnd(String phoneNumber){
        List<LoanApplicationStatus> statuses = List.of(LoanApplicationStatus.PENDING, LoanApplicationStatus.UNDER_REVIEW);
        LoanApplication loanApplication = loanApplicationRepository.findFirstByPhoneNumberAndStatusIn(phoneNumber, statuses);
        if(Objects.nonNull(loanApplication)){
            throw HttpUtil.getResolvedException(ResponseCode.FAILED_MODEL, messageUtil.getMessage("loan.application.phone-number.exist"));
        }
    }

    public void validateAccountExistenceForLoan(String phoneNumber){
        List<AccountRequest> accountRequests = accountRequestRepository.findAllByPhoneNumber(phoneNumber);
        if(accountRequests.isEmpty()){
            throw HttpUtil.getResolvedException(ResponseCode.FAILED_MODEL, messageUtil.getMessage("loan.application.account-required"));
        }
    }
}
