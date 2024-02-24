package com.harald.emicalculatorbe.service;

import com.harald.emicalculatorbe.dto.EmiResponseDto;
import com.harald.emicalculatorbe.dto.EmiRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.harald.emicalculatorbe.testutils.ObjectMocks.EMI_REQUEST_MOCK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmiCalculatorServiceTests {

    @InjectMocks
    private EmiCalculatorService emiCalculatorService;


    @Test
    void calculateEmi__NoInterestRate_Successful() {
        // given
        EmiRequestDto emiRequestDto = EmiRequestDto.builder().loanAmount(1000.00).interestRatePercentageYearly(0.00).loanTermYears(1).build();
        final double expectedResult = 83.33;

        // when
        final EmiResponseDto result = emiCalculatorService.calculateEmi(emiRequestDto);

        // then
        assertNotNull(result);
        assertEquals(expectedResult, result.getEmi());

    }

    @Test
    void calculateEmi_Minimal_InterestRate_Successful() {
        // given
        EmiRequestDto emiRequestDto = EmiRequestDto.builder().loanAmount(1000.00).interestRatePercentageYearly(0.01).loanTermYears(1).build();
        final double expectedResult = 83.34;

        // when
        final EmiResponseDto result = emiCalculatorService.calculateEmi(emiRequestDto);

        // then
        assertNotNull(result);
        assertEquals(expectedResult, result.getEmi());

    }

    @Test
    void calculateEmi_Successful() {
        // given
        final double expectedResult = 14.35;

        // when
        final EmiResponseDto result = emiCalculatorService.calculateEmi(EMI_REQUEST_MOCK);

        // then
        assertNotNull(result);
        assertEquals(expectedResult, result.getEmi());
    }

    @Test
    void calculateEmi_HigherNumbers_Successful() {
        // given
        EmiRequestDto emiRequestDto = EmiRequestDto.builder().loanAmount(10000.00).interestRatePercentageYearly(10.00).loanTermYears(30).build();
        final double expectedResult = 87.76;

        // when
        final EmiResponseDto result = emiCalculatorService.calculateEmi(emiRequestDto);

        // then
        assertNotNull(result);
        assertEquals(expectedResult, result.getEmi());
    }



}
