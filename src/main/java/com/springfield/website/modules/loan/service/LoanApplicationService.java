package com.springfield.website.modules.loan.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.modules.loan.model.BusinessLocation;
import com.springfield.website.modules.loan.payload.*;

import java.util.List;

public interface LoanApplicationService {
    OmnixApiResponse<CreditBreakdownResponse> processLoanCalculationCreditBreakdown(CreditBreakdownRequest creditBreakdownRequest);

    OmnixApiResponse<LoanApplicationResponseData> processLoanApplication(LoanApplicationRequestPayload requestPayload);

    OmnixApiResponse<List<BusinessTypeResponseData>> processFetchBusinessTypes();

    OmnixApiResponse<List<CollateralTypeResponseData>> processFetchCollateralTypes();

    OmnixApiResponse<List<BusinessLocationResponseData>> processFetchBusinessLocation();
}
