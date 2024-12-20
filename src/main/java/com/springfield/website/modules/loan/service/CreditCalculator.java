package com.springfield.website.modules.loan.service;

import com.springfield.website.modules.loan.payload.CreditBreakdownRequest;
import com.springfield.website.modules.loan.payload.CreditBreakdownResponse;
import com.springfield.website.modules.loan.payload.CreditMonthlySchedule;
import com.springfield.website.modules.loan.payload.CreditScheduleSummary;
import com.springfield.website.utils.OmnixFinUtils;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CreditCalculator {


    public static CreditBreakdownResponse calculateCreditBreakdown(CreditBreakdownRequest request){
        BigDecimal monthlyEqualPrincipal = OmnixFinUtils.divide(request.getTotalAmount(), request.getTenure());
        List<CreditMonthlySchedule> schedules = new ArrayList<>();

        double currentInterestRate = request.getInterestRate();
        BigDecimal currentOutstanding = request.getTotalAmount();
        for(int i  = 1; i <= request.getTenure(); i++){
            BigDecimal currentInterestDec= OmnixFinUtils.divide(OmnixFinUtils.ofFinDouble(currentInterestRate), 100);
            log.info("Current Interest: {}", currentInterestDec);
            BigDecimal currentInterestAmount = OmnixFinUtils.setFinScale(currentInterestDec.multiply(currentOutstanding));
            BigDecimal monthlyRepayment = OmnixFinUtils.setFinScale(monthlyEqualPrincipal.add(currentInterestAmount));
            currentOutstanding = OmnixFinUtils.setFinScale(currentOutstanding.subtract(monthlyEqualPrincipal));
             CreditMonthlySchedule monthlySchedule = CreditMonthlySchedule.builder()
                     .monthValue(i)
                     .monthlyPrincipal(monthlyEqualPrincipal)
                     .monthlyInterestAmount(currentInterestAmount)
                     .monthlyRepayment(monthlyRepayment)
                     .outstanding(currentOutstanding)
                     .build();
             schedules.add(monthlySchedule);
             currentInterestRate += request.getInterestIncrement();
        }
        BigDecimal totalRepayment = schedules.stream().map(CreditMonthlySchedule::getMonthlyRepayment).reduce(BigDecimal::add).orElse(OmnixFinUtils.setFinScale(BigDecimal.ZERO));
        BigDecimal averageMonthlyRepayment = OmnixFinUtils.divide(totalRepayment, request.getTenure());
        BigDecimal totalInterest = schedules.stream().map(CreditMonthlySchedule::getMonthlyInterestAmount).reduce(BigDecimal::add).orElse(OmnixFinUtils.setFinScale(BigDecimal.ZERO));

        CreditScheduleSummary scheduleSummary = CreditScheduleSummary.builder()
                .monthlySchedules(schedules)
                .averageMonthlyRepayment(averageMonthlyRepayment)
                .totalRepayment(totalRepayment)
                .totalInterest(totalInterest)
                .build();
        return CreditBreakdownResponse.builder()
                .totalAmount(request.getTotalAmount())
                .tenure(request.getTenure())
                .interestRate(request.getInterestRate())
                .interestIncrement(request.getInterestIncrement())
                .totalInterest(totalInterest)
                .averageMonthlyRepayment(OmnixFinUtils.setFinScale(averageMonthlyRepayment.setScale(0, RoundingMode.CEILING)))
                .totalRepayment(OmnixFinUtils.setFinScale(totalRepayment.setScale(0, RoundingMode.CEILING)))
                .scheduleSummary(scheduleSummary)
                .build();
    }

    public static CreditBreakdownResponse calculateCreditBreakdown(BigDecimal totalAmount, int tenure, double interestRate){
        CreditBreakdownRequest request = CreditBreakdownRequest.builder()
                .totalAmount(totalAmount)
                .tenure(tenure)
                .interestRate(interestRate)
                .interestIncrement(0)
                .build();
        return calculateCreditBreakdown(request);
    }

    public static CreditBreakdownResponse calculateCreditBreakdown(BigDecimal totalAmount, int tenure, double interestRate, double interestRateIncrement){
        CreditBreakdownRequest request = CreditBreakdownRequest.builder()
                .totalAmount(totalAmount)
                .tenure(tenure)
                .interestRate(interestRate)
                .interestIncrement(interestRateIncrement)
                .build();
        return calculateCreditBreakdown(request);
    }
}
