package com.harald.emicalculatorbe.mapper;

import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.pojo.EmiCalculationInput;
import org.jetbrains.annotations.NotNull;

public class EmiRequestMapper {

    private static final EmiRequestMapper INSTANCE = new EmiRequestMapper();

    // Public static method to provide access to the singleton instance
    public static EmiRequestMapper getInstance() {
        return INSTANCE;
    }

    public EmiCalculationInput toEmiCalculationInput(@NotNull final EmiRequestDto emiRequestDto) {
       // TODO: Add error handling for nullpointers

        EmiCalculationInput result =  EmiCalculationInput.builder().build();

        result.setLoanAmount(emiRequestDto.getLoanAmount());
        result.setTenureMonths(emiRequestDto.getLoanTermYears() * 12);

        double interestRateAbsoluteMonthly = emiRequestDto.getInterestRatePercentageYearly() / 12 / 100;
        result.setInterestRateMonthlyAbsolute(
                interestRateAbsoluteMonthly);

        return result;
    }


}
