package com.harald.emicalculatorbe.controller;

import com.harald.emicalculatorbe.dto.EmiDto;
import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.service.EmiCalculatorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.harald.emicalculatorbe.constants.EndpointConstants.API_EMI_URL;


@RestController
@RequestMapping(API_EMI_URL)
public class EmiCalculatorRestController {

    private final EmiCalculatorService emiCalculatorService;

    public EmiCalculatorRestController(final EmiCalculatorService emiCalculatorService
    ) {
        this.emiCalculatorService = emiCalculatorService;
    }

    @PostMapping()
    public ResponseEntity<EmiDto> calculateEmiValue(@Valid @RequestBody final EmiRequestDto emiRequestDto) {


        EmiDto response = emiCalculatorService.calculateEmi(emiRequestDto);

        return ResponseEntity.ok(response);
    }

}
