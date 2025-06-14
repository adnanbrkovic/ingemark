package com.ingemark.zadatak.service;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal convertEurAmountToCurrencyHnbMeanRate(BigDecimal eurAmount, String currencyCode);

    BigDecimal convertEurAmountToUsdHnbMeanRate(BigDecimal eurAmount);

}
