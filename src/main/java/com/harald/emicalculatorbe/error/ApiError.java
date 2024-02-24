package com.harald.emicalculatorbe.error;

import com.harald.emicalculatorbe.constants.ErrorMessageConstants;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Getter
@Slf4j
public class ApiError {

    private final Instant timestamp = Instant.now();
    private HttpStatusCode status;
    private List<String> errors;


    public ApiError(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatusCode status, String error) {
        super();
        this.status = status;
        errors = Collections.singletonList(error);
    }

     public ApiError(@NonNull final Exception ex) {
        this.errors.add(ErrorMessageConstants.DEFAULT_ERROR);
    }


}
