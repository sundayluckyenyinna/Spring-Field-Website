package com.springfield.website.modules.career.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "career_job_application")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CareerJobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String guid;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String resume;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @Enumerated(value = EnumType.STRING)
    private JobApplicationStatus status;

    @PrePersist
    public void prePersist(){
        if(Objects.isNull(this.getCreatedAt())){
            this.setCreatedAt(DateUtils.getCurrentDateTime());
        }
        if(Objects.isNull(this.getUpdatedAt())){
            this.setUpdatedAt(DateUtils.getCurrentDateTime());
        }
        if(CommonUtil.isNullOrEmpty(this.getGuid())){
            this.setGuid(CommonUtil.generateGuid());
        }
    }
}
