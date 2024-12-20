package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.loan.model.BusinessType;
import com.springfield.website.modules.loan.model.CollateralType;
import com.springfield.website.modules.loan.model.LoanApplicationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplicationRequestPayload {

    private LoanApplicationType loanApplicationType;

    @NotNull(message = "location is required")
    @NotBlank(message = "location is required")
    private String location;


    @NotNull(message = "fullName is required")
    @NotBlank(message = "fullName is required")
    private String fullName;

    @NotNull(message = "email is required")
    @NotBlank(message = "email is required")
    private String email;

    @NotNull(message = "monthlyIncome is required")
    private BigDecimal monthlyIncome = BigDecimal.ZERO;

    @NotNull(message = "businessName is required")
    @NotBlank(message = "businessName is required")
    private String businessName;

    @NotNull(message = "loanAmount is required")
    private BigDecimal loanAmount;

    @NotNull(message = "businessType is required")
    private BusinessType businessType;

    @NotNull(message = "collateralType is required")
    private CollateralType collateralType;

    @NotNull(message = "homeAddress is required")
    private String homeAddress;

    @NotNull(message = "businessAddress is required")
    private String businessAddress;
}
