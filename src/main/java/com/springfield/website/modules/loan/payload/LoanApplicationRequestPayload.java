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
import org.springframework.format.annotation.DateTimeFormat;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplicationRequestPayload {

    @NotNull(message = "fullName is required")
    @NotBlank(message = "fullName is required")
    private String fullName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String dateOfBirth;

    private String gender;

    @NotNull(message = "email is required")
    @NotBlank(message = "email is required")
    private String email;

    private String phoneNumber;

    private String stateOfResidence;

    private String contactAddress;

    @NotNull(message = "loanAmount is required")
    private BigDecimal loanAmount;

    private String loanPurpose;

    private int loanTenure;
}
