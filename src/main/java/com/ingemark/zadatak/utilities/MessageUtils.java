package com.ingemark.zadatak.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtils {

    @Autowired
    private MessageSource messageSource;

    /*
    Wrapper metoda povrh messageSource.getMessage() da se enkapsulira koristenje localea
     */
    public String getMessage(String messageCode,  Object[] args){

        return messageSource.getMessage(
                "product.price.min",
                args,
                getLocale()
            );
    }

    /*
    Vraća defaultni locale
     */
    private Locale getLocale(){
        return LocaleContextHolder.getLocale();
    }
}
