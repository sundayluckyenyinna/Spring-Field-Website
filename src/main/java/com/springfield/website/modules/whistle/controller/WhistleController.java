package com.springfield.website.modules.whistle.controller;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.RequestMetaArg;
import com.springfield.website.modules.whistle.payload.ContactUsRequestPayload;
import com.springfield.website.modules.whistle.payload.WhistleBlowerRequestPayload;
import com.springfield.website.modules.whistle.service.WhistleBlowerService;
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
@Tag(name = "Dispute Management Service", description = "Dispute management service")
@RequestMapping(value = "/enquiry", produces = MediaType.APPLICATION_JSON_VALUE)
public class WhistleController {

    private final WhistleBlowerService whistleBlowerService;


    @PostMapping(value = "/whistle/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Submit Whistle enquiry", description = "Submit whistle enquiry")
    public ResponseEntity<OmnixApiResponse<String>> submitWhistle(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Valid @RequestBody WhistleBlowerRequestPayload requestPayload
    ){
        return ResponseEntity.ok(whistleBlowerService.processWhistleBlowerSubmission(requestMeta, requestPayload));
    }

    @PostMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "ContactUS enquiry", description = "ContactUs enquiry")
    public ResponseEntity<OmnixApiResponse<String>> contactUsEnquiry(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Valid @RequestBody ContactUsRequestPayload requestPayload
    ){
        return ResponseEntity.ok(whistleBlowerService.processContactUsSubmission(requestMeta, requestPayload));
    }
}
