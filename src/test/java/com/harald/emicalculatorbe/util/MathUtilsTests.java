package com.harald.emicalculatorbe.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MathUtilsTests {

    @Test
    void roundTwoDecimals_Successful() {
        // given
        double expectedResult = 123.12;

        // when
       final double result =  MathUtils.roundTwoDecimals(123.123);

       // then
        assertEquals(expectedResult, result);
    }

}
