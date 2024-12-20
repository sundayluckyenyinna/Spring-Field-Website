package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class CreditBreakdownRequest {

    @NotNull(message = "totalAmount is required")
    private BigDecimal totalAmount;

    @NotNull(message = "tenure is required")
    private Integer tenure;

    private double interestRate;

    private double interestIncrement = 0;
}
