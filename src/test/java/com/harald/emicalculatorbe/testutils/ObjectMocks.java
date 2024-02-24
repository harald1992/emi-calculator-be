package com.harald.emicalculatorbe.testutils;

import com.harald.emicalculatorbe.dto.EmiResponseDto;
import com.harald.emicalculatorbe.dto.EmiRequestDto;

public class ObjectMocks {
    public static final EmiRequestDto EMI_REQUEST_MOCK = EmiRequestDto.builder().loanAmount(1000.00).loanTermYears(10).interestRatePercentageYearly(12.00).build();

    public static final EmiResponseDto EMI_RESPONSE_MOCK = EmiResponseDto.builder().emi(123.00).build();

}
