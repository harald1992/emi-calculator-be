package com.harald.emicalculatorbe.controller;

import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.dto.EmiResponseDto;
import com.harald.emicalculatorbe.service.EmiCalculatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.Objects;
import java.util.Set;

import static com.harald.emicalculatorbe.testutils.ObjectMocks.EMI_REQUEST_MOCK;
import static com.harald.emicalculatorbe.testutils.ObjectMocks.EMI_RESPONSE_MOCK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestConfiguration
class TestContextConfiguration {
    @Bean
    public MethodValidationPostProcessor bean() {
        return new MethodValidationPostProcessor();
    }
}

@ExtendWith(MockitoExtension.class)
@Import(ValidationAutoConfiguration.class)
@ContextConfiguration(classes = { EmiCalculatorRestController.class, ValidationAutoConfiguration.class})
public class EmiCalculatorRestControllerTests {

    @InjectMocks
    EmiCalculatorRestController controller;

    @Mock
    private EmiCalculatorService emiCalculatorService;


    private static ValidatorFactory validatorFactory;

    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    void calculateEmiValue_Successful() {
        // given
        when(emiCalculatorService.calculateEmi(EMI_REQUEST_MOCK)).thenReturn(EMI_RESPONSE_MOCK);
        Set<ConstraintViolation<EmiRequestDto>> violations = validator.validate(EMI_REQUEST_MOCK);

        // when
        final ResponseEntity<EmiResponseDto> result = controller.calculateEmiValue(EMI_REQUEST_MOCK);

        // then
        assertTrue(violations.isEmpty());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(EMI_RESPONSE_MOCK.getEmi(), Objects.requireNonNull(result.getBody()).getEmi() );
    }

    @Test
    void calculateEmiValue_Validation_Error() {
        // given
        EmiRequestDto requestDto = EmiRequestDto.builder().build();
        Set<ConstraintViolation<EmiRequestDto>> violations = validator.validate(requestDto);

        // then
        assertFalse(violations.isEmpty());  // leading to an exception returning ApiError via global exception handler
    }

}
