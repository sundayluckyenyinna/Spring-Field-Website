package com.springfield.website.modules.account.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequestPayload {
    private String productType;
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private String stateOfResidence;
    private String contactAddress;
    private String bvn;
    private String nin;
    private String meansOfIdentification;
    private String documentUrl;
    private String utilityBillUrl;
    private String otherDocUrl;
}
