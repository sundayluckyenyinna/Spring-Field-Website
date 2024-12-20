package com.springfield.website.modules.loan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Enumerated(value = EnumType.STRING)
    private LoanApplicationType loanApplicationType;

    private String location;

    private String fullName;
    private String email;
    private BigDecimal monthlyIncome;
    private String businessName;
    private BigDecimal loanAmount;

    @Enumerated(value = EnumType.STRING)
    private BusinessType businessType;

    @Enumerated(value = EnumType.STRING)
    private CollateralType collateralType;

    private String homeAddress;

    private String businessAddress;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

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
