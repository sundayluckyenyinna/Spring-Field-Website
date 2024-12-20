package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class CreditMonthlySchedule {

    private Integer monthValue;
    private BigDecimal monthlyPrincipal;
    private BigDecimal monthlyInterestAmount;
    private BigDecimal monthlyRepayment;
    private BigDecimal outstanding;
}
