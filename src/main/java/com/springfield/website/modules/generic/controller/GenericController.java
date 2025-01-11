package com.springfield.website.modules.generic.controller;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.RequestMetaArg;
import com.springfield.website.modules.generic.payload.CountryStateResponseData;
import com.springfield.website.modules.generic.service.GenericService;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import com.springfield.website.utils.StringValues;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/generic", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Generic controller", description = "Generic controller")
public class GenericController {

    private final GenericService genericService;

    @PostMapping(value = "/file-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Upload any file", description = "Upload any file")
    public ResponseEntity<OmnixApiResponse<RemoteFileUploadResponse>> uploadMultipartFile(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(genericService.processRemoteFileUpload(multipartFile));
    }

    @GetMapping(value = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Fetch a set of states", description = "Fetch a set of states by country code")
    public ResponseEntity<OmnixApiResponse<Set<CountryStateResponseData>>> getCountryState(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Parameter(example = "NG") @RequestParam(value = "countryCode", required = false, defaultValue = "NG") String countryCode
    ){
        return ResponseEntity.ok(genericService.processFetchCountryStates(null, countryCode));
    }

    @GetMapping(value = "/states-lga", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get local government area in a state", description = "Get local government area in a state")
    public ResponseEntity<OmnixApiResponse<Set<String>>> getStateLocalGovt(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Parameter(example = "NG") @RequestParam(value = "countryCode") String countryCode,
            @Parameter(example = "LAGOS") @RequestParam(value = "state") String stateEnum
    ){
        return ResponseEntity.ok(genericService.processFetchStateLocalGovt(requestMeta, countryCode, stateEnum));
    }
}
