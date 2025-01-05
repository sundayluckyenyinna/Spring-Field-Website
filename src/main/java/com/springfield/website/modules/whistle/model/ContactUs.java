package com.springfield.website.modules.whistle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.whistle.payload.ContactUsRequestPayload;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_us")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String guid;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String subject;
    private String enquiryType;
    private String description;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static ContactUs fromRequest(ContactUsRequestPayload requestPayload){
        return ContactUs.builder()
                .guid(CommonUtil.generateGuid())
                .fullName(requestPayload.getFullName())
                .phoneNumber(requestPayload.getPhoneNumber())
                .email(requestPayload.getEmail())
                .subject(requestPayload.getSubject())
                .enquiryType(requestPayload.getEnquiryType())
                .description(requestPayload.getDescription())
                .createdAt(DateUtils.getCurrentDateTime())
                .updatedAt(DateUtils.getCurrentDateTime())
                .build();
    }
}
