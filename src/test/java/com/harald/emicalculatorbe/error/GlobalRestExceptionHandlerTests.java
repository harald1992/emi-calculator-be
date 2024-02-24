package com.harald.emicalculatorbe.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalRestExceptionHandlerTests {

    @InjectMocks
    GlobalRestExceptionHandler exceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Test
    void handleMethodArgumentNotValid() {
        // given
        // final ArgumentCaptor<ApiError> captor = ArgumentCaptor.forClass(ApiError.class);
        List<String> expectedErrors = new ArrayList<>();
        expectedErrors.add("Loan amount is mandatory.");

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("emiRequestDto", "loanAmount", expectedErrors.get(0)));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        List<ObjectError> objectErrors = new ArrayList<>();
        objectErrors.add(new ObjectError("emiRequestName", "Other error regarding the whole object"));
        when(bindingResult.getGlobalErrors()).thenReturn(objectErrors);

        // when
        ResponseEntity<Object> responseEntity = exceptionHandler.handleMethodArgumentNotValid(
                ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);


        // then
        assertNotNull(responseEntity);
        ApiError apiError = (ApiError) responseEntity.getBody();
        assertNotNull(apiError);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrors, apiError.getErrors());


    }

}
