package com.harald.emicalculatorbe.service;

import com.harald.emicalculatorbe.dto.EmiDto;
import com.harald.emicalculatorbe.dto.EmiRequestDto;
import com.harald.emicalculatorbe.mapper.EmiRequestMapper;
import com.harald.emicalculatorbe.pojo.EmiCalculationInput;
import com.harald.emicalculatorbe.utils.MathUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class EmiCalculatorService {

    public EmiDto calculateEmi(@NotNull final EmiRequestDto emiRequestDto) {
        EmiCalculationInput input = EmiRequestMapper.getInstance().toEmiCalculationInput(emiRequestDto);
        double rawEmi;

        if (emiRequestDto.getInterestRatePercentageYearly() == 0) {
            rawEmi = input.getLoanAmount() / input.getTenureMonths();
        } else {
            final double compoundingFactor = Math.pow(1 + input.getInterestRateMonthlyAbsolute(), input.getTenureMonths());
            final double discountingFactor = Math.pow(1 + input.getInterestRateMonthlyAbsolute(), input.getTenureMonths()) - 1;
            rawEmi = input.getLoanAmount() * input.getInterestRateMonthlyAbsolute() * compoundingFactor / discountingFactor;
        }

        double roundedEmi = MathUtils.roundTwoDecimals(rawEmi);
        return EmiDto.builder().emi(roundedEmi).build();
    }

}
