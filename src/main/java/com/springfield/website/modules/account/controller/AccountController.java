package com.springfield.website.modules.account.controller;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.RequestMetaArg;
import com.springfield.website.modules.account.payload.AccountRequestPayload;
import com.springfield.website.modules.account.service.AccountService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Account management service", description = "Account management service")
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/submit")
    @Operation(summary = "Submit request for account opening", description = "Submit request for account opening")
    public ResponseEntity<OmnixApiResponse<String>> submitAccountRequest(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Valid @RequestBody AccountRequestPayload requestPayload
    ){
        return ResponseEntity.ok(accountService.processAccountOpening(requestMeta, requestPayload));
    }
}
