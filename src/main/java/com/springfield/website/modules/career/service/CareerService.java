package com.springfield.website.modules.career.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.OmnixPage;
import com.springfield.website.common.PageParameters;
import com.springfield.website.modules.career.payload.CareerJobResponseData;
import com.springfield.website.modules.career.payload.JobApplicationPayload;

public interface CareerService {
    OmnixApiResponse<OmnixPage<CareerJobResponseData>> processFetchCareerJob(PageParameters pageParameters, String searchKey);

    OmnixApiResponse<JobApplicationPayload.JobApplicationResponsePayload> processJobApplicationSubmission(JobApplicationPayload.JobApplicationRequestPayload requestPayload);
}
