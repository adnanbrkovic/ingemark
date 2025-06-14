package com.ingemark.zadatak.facade.impl;

import com.ingemark.zadatak.domain.facade.exchangerate.hnb.HnbRateResponse;
import com.ingemark.zadatak.domain.model.CurrencyCodes;
import com.ingemark.zadatak.exception.ValidationException;
import com.ingemark.zadatak.facade.HnbFacade;
import com.ingemark.zadatak.utilities.MessageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class HnbFacadeImpl implements HnbFacade {

    @Value("${exchangerate.hnb.baseurl}")
    private String hnbBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageUtils messageUtils;


    @Override
    public HnbRateResponse getRateByCurrencyCode(String code){

        String url = new StringBuilder(hnbBaseUrl).append("?valuta=").append(code).toString();

        ResponseEntity<List<HnbRateResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HnbRateResponse>>() {}
        );

        List<HnbRateResponse> rateList = response.getBody();
        if(rateList.isEmpty()){
            throw new RuntimeException(messageUtils.getMessage("exchangerate.hnb.notexists",
                    new Object[]{code}));

        }
        HnbRateResponse rate = rateList.get(0);
        log.info("HNB get rate url = {}, rate value = {}", url, rate);
        return rate;

    }

    //Wrapper metoda za stvari koje nam trebaju u aplikaciji (napravljene zbog pojednostavljenja poziva i cisceg koda)
    @Override
    public HnbRateResponse getUsdRate(){
        return getRateByCurrencyCode(CurrencyCodes.USD.name());
    }


}
