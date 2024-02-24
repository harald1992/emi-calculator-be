package com.harald.emicalculatorbe.mapper;

import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.pojo.EmiCalculationInput;
import org.jetbrains.annotations.NotNull;

public class EmiRequestMapper {

    private static final EmiRequestMapper INSTANCE = new EmiRequestMapper();

    public static EmiRequestMapper getInstance() {
        return INSTANCE;
    }

    public EmiCalculationInput toEmiCalculationInput(@NotNull final EmiRequestDto emiRequestDto) {
        if (emiRequestDto.getLoanAmount() == null || emiRequestDto.getInterestRatePercentageYearly() == null || emiRequestDto.getLoanTermYears() == null) {
            throw new IllegalArgumentException("Loan amount, interest rate, and loan term must not be null");
        }
        EmiCalculationInput result =  EmiCalculationInput.builder().build();

        result.setLoanAmount(emiRequestDto.getLoanAmount());
        result.setTenureMonths(emiRequestDto.getLoanTermYears() * 12);
        result.setInterestRateMonthlyAbsolute(
                emiRequestDto.getInterestRatePercentageYearly() / 12 / 100);

        return result;
    }


}
