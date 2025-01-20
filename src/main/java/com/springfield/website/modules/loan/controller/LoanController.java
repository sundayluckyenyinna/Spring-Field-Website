package com.springfield.website.modules.loan.controller;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.RequestMetaArg;
import com.springfield.website.modules.loan.payload.*;
import com.springfield.website.modules.loan.service.LoanApplicationService;
import com.springfield.website.utils.StringValues;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Loan management service", description = "Loan management service")
public class LoanController {

    private final LoanApplicationService loanApplicationService;


    @PostMapping(value = "/credit-calculator", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Credit loan calculator", description = "Loan credit calculator")
    public ResponseEntity<OmnixApiResponse<CreditBreakdownResponse>> loanCalculator(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Valid @RequestBody CreditBreakdownRequest request
    ){
        return ResponseEntity.ok(loanApplicationService.processLoanCalculationCreditBreakdown(request));
    }

    @PostMapping(value = "/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Apply for loan", description = "Apply for loan")
    public ResponseEntity<OmnixApiResponse<LoanApplicationResponseData>> loanApplication(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Valid @RequestBody LoanApplicationRequestPayload requestPayload
    ){
        return ResponseEntity.ok(loanApplicationService.processLoanApplication(requestMeta, requestPayload));
    }


    @GetMapping(value = "/business-types")
    @Operation(summary = "Get business types", description = "Get business types")
    public ResponseEntity<OmnixApiResponse<List<BusinessTypeResponseData>>> getBusinessTypes(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta
    ){
        return ResponseEntity.ok(loanApplicationService.processFetchBusinessTypes());
    }

    @GetMapping(value = "/collateral-types")
    @Operation(summary = "Get collateral types", description = "Get collateral types")
    public ResponseEntity<OmnixApiResponse<List<CollateralTypeResponseData>>> getCollateralTypes(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta
    ){
        return ResponseEntity.ok(loanApplicationService.processFetchCollateralTypes());
    }

    @GetMapping(value = "/business-locations")
    @Operation(summary = "Get business locations", description = "Get business location")
    public ResponseEntity<OmnixApiResponse<List<BusinessLocationResponseData>>> getBusinessLocations(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta
    ){
        return ResponseEntity.ok(loanApplicationService.processFetchBusinessLocation());
    }
}
