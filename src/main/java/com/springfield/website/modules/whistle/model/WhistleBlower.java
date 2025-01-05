package com.springfield.website.modules.whistle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.whistle.payload.WhistleBlowerRequestPayload;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "whistle_blower")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WhistleBlower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String guid;

    private String enquiryType;

    @Column(columnDefinition = "text")
    private String reportDetails;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        if(CommonUtil.isNullOrEmpty(this.getGuid())){
            this.setGuid(CommonUtil.generateGuid());
        }
        if(Objects.isNull(this.getCreatedAt())){
            this.setCreatedAt(DateUtils.getCurrentDateTime());
        }
        if(Objects.isNull(this.getUpdatedAt())){
            this.setUpdatedAt(DateUtils.getCurrentDateTime());
        }
    }

    public static WhistleBlower fromRequest(WhistleBlowerRequestPayload requestPayload){
        return WhistleBlower.builder()
                .guid(CommonUtil.generateGuid())
                .enquiryType(requestPayload.getEnquiryType())
                .reportDetails(requestPayload.getDetails())
                .createdAt(DateUtils.getCurrentDateTime())
                .updatedAt(DateUtils.getCurrentDateTime())
                .build();
    }
}
