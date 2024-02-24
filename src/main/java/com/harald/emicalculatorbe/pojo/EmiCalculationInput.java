package com.harald.emicalculatorbe.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmiCalculationInput {

    private double loanAmount;   // p
    private int tenureMonths; // period in months, also the number of payments
    private double interestRateMonthlyAbsolute; // in absolute instead of percentage

}
