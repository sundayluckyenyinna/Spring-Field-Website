package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditScheduleSummary {

    private List<CreditMonthlySchedule> monthlySchedules;
    private BigDecimal averageMonthlyRepayment;
    private BigDecimal totalRepayment;
    private BigDecimal totalInterest;
}
