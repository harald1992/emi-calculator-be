package com.harald.emicalculatorbe.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmiRequestDto {

    @NotNull(message = "Loan amount is mandatory.")
    @Min(value=0, message="Loan amount must be a positive number.")
    @Digits(integer = 10, fraction = 2, message = "Loan amount must have up to 10 digits and 2 decimal places.")
    private Double loanAmount;

    @NotNull(message = "Interest rate  is mandatory.")
    @Min(value=0, message="Interest rate must be between 0 and 100.")
    @Max(value=100, message="Interest rate must be between 0 and 100.")
    private Double interestRatePercentageYearly;

    @NotNull(message = "Loan term is mandatory.")
    @Min(value=0, message="Loan term must be between 0 and 30 years.")
    @Max(value=30, message="Loan term must be between 0 and 30 years.")
    private Integer loanTermYears;   // tenure:
}
