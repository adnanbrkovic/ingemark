package com.ingemark.zadatak.service.impl;

import com.ingemark.zadatak.domain.facade.exchangerate.hnb.HnbRateResponse;
import com.ingemark.zadatak.domain.model.CurrencyCodes;
import com.ingemark.zadatak.facade.HnbFacade;
import com.ingemark.zadatak.service.ExchangeRateService;
import com.ingemark.zadatak.utilities.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
Klasa koja se enkapsulira operacije nad iznosima u razlicitim valutama, moze dohvacati tecajeve iz vise razlicitih banaka,
u primjeru zadadatka koristi samo HNB kao izvor
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private HnbFacade hnbFacade;

    @Autowired
    private MessageUtils messageUtils;

    @Override
    public BigDecimal convertEurAmountToCurrencyHnbMeanRate(BigDecimal eurAmount, String currencyCode){

        if(eurAmount == null){
            throw new IllegalArgumentException(messageUtils.getMessage("exchangerate.amount.null", null));
        }
        if(currencyCode == null || currencyCode.isBlank()){
            throw new IllegalArgumentException(messageUtils.getMessage("eexchangerate.currencycode.null", null));
        }

        HnbRateResponse response = hnbFacade.getUsdRate();

        BigDecimal convertedAmount = eurAmount.multiply(response.getSrednjiTecajAsBigDecimal()).setScale(2, RoundingMode.HALF_UP);
        return convertedAmount;

    }

    //Wrapper metoda za stvari koje nam trebaju u aplikaciji (napravljene zbog pojednostavljenja poziva i cisceg koda)
    @Override
    public BigDecimal convertEurAmountToUsdHnbMeanRate(BigDecimal eurAmount){

        return convertEurAmountToCurrencyHnbMeanRate(eurAmount, CurrencyCodes.USD.name());

    }

}
