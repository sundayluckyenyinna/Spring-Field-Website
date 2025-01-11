package com.springfield.website.modules.loan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan_application")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String guid;

    @Enumerated(value = EnumType.STRING)
    private LoanApplicationStatus status;

    private String fullName;

    private LocalDate dateOfBirth;

    private String gender;

    private String email;

    private String phoneNumber;

    private String stateOfResidence;

    private String contactAddress;

    private BigDecimal loanAmount;

    private String loanPurpose;

    private int loanTenure;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private String businessAddress;

    @Enumerated(value = EnumType.STRING)
    private BusinessType businessType;

    private String bvn;

    private String nin;

    @PrePersist
    public void prePersist(){
        if(Objects.isNull(this.getCreatedAt())){
            this.setCreatedAt(DateUtils.getCurrentDateTime());
        }
        if(Objects.isNull(this.getUpdatedAt())){
            this.setUpdatedAt(DateUtils.getCurrentDateTime());
        }
    }
}
