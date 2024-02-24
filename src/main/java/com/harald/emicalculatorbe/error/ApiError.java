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

    private List<String> errors;

    public ApiError( final List<String> errors) {
        super();
        this.errors = errors;
    }

    public ApiError( final String error) {
        super();
        this.errors = Collections.singletonList(error);
    }

    public ApiError(final Exception ex) {
        this.errors.add(ErrorMessageConstants.DEFAULT_ERROR);
    }


}
