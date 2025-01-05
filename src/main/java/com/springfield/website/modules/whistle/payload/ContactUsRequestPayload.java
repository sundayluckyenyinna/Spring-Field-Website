package com.springfield.website.modules.whistle.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUsRequestPayload {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String subject;
    private String enquiryType;
    private String description;
}
