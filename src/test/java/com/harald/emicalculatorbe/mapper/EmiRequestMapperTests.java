package com.harald.emicalculatorbe.mapper;

import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.pojo.EmiCalculationInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmiRequestMapperTests {

    @InjectMocks
    private EmiRequestMapper mapper;

    @Test
    void mapEmiRequest_Successful() {
        EmiRequestDto emiRequestDto = EmiRequestDto.builder().loanAmount(1000.00).interestRatePercentageYearly(12.00).loanTermYears(2).build();
        final EmiCalculationInput expectedResult = EmiCalculationInput.builder().loanAmount(1000).interestRateMonthlyAbsolute(0.01).tenureMonths(24).build();

        final EmiCalculationInput result = mapper.toEmiCalculationInput(emiRequestDto);

        assertNotNull(result);
        assertEquals(result, expectedResult);

    }

}
