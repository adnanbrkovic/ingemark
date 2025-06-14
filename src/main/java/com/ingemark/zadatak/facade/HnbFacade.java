package com.ingemark.zadatak.facade;

import com.ingemark.zadatak.domain.facade.exchangerate.hnb.HnbRateResponse;

import java.util.List;

public interface HnbFacade {

    HnbRateResponse getRateByCurrencyCode(String code);

    HnbRateResponse getUsdRate();


}
