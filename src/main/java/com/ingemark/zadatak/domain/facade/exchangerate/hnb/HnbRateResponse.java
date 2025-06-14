package com.ingemark.zadatak.domain.facade.exchangerate.hnb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HnbRateResponse implements Serializable {

    @JsonProperty("broj_tecajnice")
    private String brojTecajnice;

    @JsonProperty("datum_primjene")
    private String datumPrimjene;

    @JsonProperty("drzava")
    private String drzava;

    @JsonProperty("drzava_iso")
    private String drzavaIso;

    @JsonProperty("kupovni_tecaj")
    private String kupovniTecaj;

    @JsonProperty("prodajni_tecaj")
    private String prodajniTecaj;

    @JsonProperty("sifra_valute")
    private String sifraValute;

    @JsonProperty("srednji_tecaj")
    private String srednjiTecaj;

    @JsonProperty("valuta")
    private String valuta;
}
