package com.springfield.website.modules.account.entities;

import com.springfield.website.modules.account.enums.AccountRequestStatus;
import com.springfield.website.modules.account.payload.AccountRequestPayload;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_request")
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
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

    @Column(nullable = false)
    private AccountRequestStatus status = AccountRequestStatus.PENDING;

    @PrePersist
    public void prePersist(){
        if(Objects.isNull(this.getCreatedAt())){
            this.setCreatedAt(DateUtils.getCurrentDateTime());
        }
        if(Objects.isNull(this.getUpdatedAt())){
            this.setUpdatedAt(DateUtils.getCurrentDateTime());
        }
        if(Objects.isNull(this.getStatus())){
            this.setStatus(AccountRequestStatus.PENDING);
        }
    }

    public static AccountRequest fromRequest(AccountRequestPayload payload){
        return AccountRequest.builder()
                .createdAt(DateUtils.getCurrentDateTime())
                .updatedAt(DateUtils.getCurrentDateTime())
                .productType(payload.getProductType())
                .fullName(payload.getFullName())
                .gender(payload.getGender())
                .dateOfBirth(payload.getDateOfBirth())
                .emailAddress(payload.getEmailAddress())
                .phoneNumber(payload.getPhoneNumber())
                .stateOfResidence(payload.getStateOfResidence())
                .contactAddress(payload.getContactAddress())
                .bvn(payload.getBvn())
                .nin(payload.getNin())
                .meansOfIdentification(payload.getMeansOfIdentification())
                .documentUrl(payload.getDocumentUrl())
                .utilityBillUrl(payload.getUtilityBillUrl())
                .otherDocUrl(payload.getOtherDocUrl())
                .status(AccountRequestStatus.PENDING)
                .build();
    }
}
