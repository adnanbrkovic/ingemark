package com.ingemark.zadatak.facade.impl;

import com.ingemark.zadatak.domain.facade.exchangerate.hnb.HnbRateResponse;
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

        String url = new StringBuilder(hnbBaseUrl).append("?").append(code).toString();
        log.info("HNB get rate url" + url);

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
        return rateList.get(0);

    }

    //Wrapper metode za stvari koje nam trebaju u aplikaciji (napravljene zbog pojednostavljenja poziva i cisceg koda)
    //public


}
