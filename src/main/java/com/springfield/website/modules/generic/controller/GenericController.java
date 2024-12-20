package com.springfield.website.modules.generic.controller;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.RequestMetaArg;
import com.springfield.website.modules.generic.service.GenericService;
import com.springfield.website.modules.generic.storage.FirebaseFtpClientService;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import com.springfield.website.utils.StringValues;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
}
