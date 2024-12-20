package com.springfield.website.modules.career.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.career.model.CareerJobApplication;
import com.springfield.website.modules.career.model.JobApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JobApplicationPayload {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JobApplicationRequestPayload{
        private String fullName;
        private String email;
        private String mobileNumber;
        private String resumeLink;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JobApplicationResponsePayload{
        private String fullName;
        private String email;
        private String mobileNumber;
        private String resumeLink;
        private String createdAt;
        private String updatedAt;
        private JobApplicationStatus status;

        public static JobApplicationResponsePayload fromJobApplication(CareerJobApplication careerJobApplication){
            return JobApplicationResponsePayload.builder()
                    .fullName(careerJobApplication.getFullName())
                    .email(careerJobApplication.getEmail())
                    .mobileNumber(careerJobApplication.getMobileNumber())
                    .resumeLink(careerJobApplication.getResume())
                    .createdAt(careerJobApplication.getCreatedAt().toString())
                    .updatedAt(careerJobApplication.getUpdatedAt().toString())
                    .status(careerJobApplication.getStatus())
                    .build();
        }
    }
}
