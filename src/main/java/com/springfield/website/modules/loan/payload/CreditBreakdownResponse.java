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
public class CreditBreakdownResponse {

    private BigDecimal totalAmount;
    private Integer tenure;
    private double interestRate;
    private double interestIncrement;
    private BigDecimal totalInterest;
    private BigDecimal averageMonthlyRepayment;
    private BigDecimal totalRepayment;

    private CreditScheduleSummary scheduleSummary;
}
