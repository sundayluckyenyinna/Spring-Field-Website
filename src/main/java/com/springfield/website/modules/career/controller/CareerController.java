package com.springfield.website.modules.career.controller;

import com.springfield.website.common.*;
import com.springfield.website.modules.career.payload.CareerJobResponseData;
import com.springfield.website.modules.career.payload.JobApplicationPayload;
import com.springfield.website.modules.career.service.CareerService;
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
@RequestMapping(value = "/careers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Career Management Service", description = "Career management service")
public class CareerController {

    private final CareerService careerService;


    @GetMapping(value = "/jobs")
    @Operation(summary = "Fetch paginated careerJobs with search functionality", description = "Fetch paginated careerJobs with search functionalities")
    public ResponseEntity<OmnixApiResponse<OmnixPage<CareerJobResponseData>>> getCareerJobs(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @RequestParam(value = StringValues.PAGE_NUMBER_KEY, required = false, defaultValue = StringValues.DEFAULT_PAGE_NUMBER) Integer pageNumber,
            @RequestParam(value = StringValues.PAGE_SIZE_KEY, required = false, defaultValue = StringValues.DEFAULT_PAGE_SIZE) Integer pageSize,
            @RequestParam(value = "search", required = false) String searchKey
    ){
        PageParameters pageParameters = PageParameters.with(pageNumber, pageSize);
        return ResponseEntity.ok(careerService.processFetchCareerJob(pageParameters, searchKey));
    }

    @PostMapping(value = "/jobs/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Apply for job", description = "Apply for job")
    public ResponseEntity<OmnixApiResponse<JobApplicationPayload.JobApplicationResponsePayload>> applyForJob(
            @RequestHeader(StringValues.CHANNEL_ID) String channelId,
            @RequestHeader(StringValues.CHANNEL_SECRET) String channelSecret,
            @Parameter(hidden = true) @RequestMetaArg RequestMeta requestMeta,
            @Valid @RequestBody JobApplicationPayload.JobApplicationRequestPayload requestPayload
    ){
        return ResponseEntity.ok(careerService.processJobApplicationSubmission(requestPayload));
    }
}
