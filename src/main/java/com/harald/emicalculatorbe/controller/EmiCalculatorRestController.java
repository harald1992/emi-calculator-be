package com.harald.emicalculatorbe.controller;

import com.harald.emicalculatorbe.dto.EmiResponseDto;
import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.service.EmiCalculatorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.harald.emicalculatorbe.constants.EndpointConstants.API_EMI_URL;


@RestController
@RequestMapping(API_EMI_URL)
@Slf4j
public class EmiCalculatorRestController {

    private final EmiCalculatorService emiCalculatorService;

    public EmiCalculatorRestController(final EmiCalculatorService emiCalculatorService
    ) {
        this.emiCalculatorService = emiCalculatorService;
    }

    @PostMapping()
    public ResponseEntity<EmiResponseDto> calculateEmiValue(@Valid @RequestBody final EmiRequestDto emiRequestDto) {
        log.info("Post request for calculateEmiValue with " + emiRequestDto.toString());
        EmiResponseDto response = emiCalculatorService.calculateEmi(emiRequestDto);
        return ResponseEntity.ok(response);
    }

}
