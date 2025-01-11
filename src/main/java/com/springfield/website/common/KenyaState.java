package com.springfield.website.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KenyaState {
    BOMET("Bomet"),
    BOYAMBA("Bomet"),
    BUNGOMA("Bungoma"),
    BUSIA("Busia"),
    EMBU("Embu"),
    GARISSA("Garissa"),
    HOMA_BAY("Homa Bay"),
    ISIOLO("Isiolo"),
    KAJIADO("Kajiado"),
    KAKAMEGA("Kakamega"),
    KERICHO("Kericho"),
    KIAMBU("Kiambu"),
    KILIFI("Kilifi"),
    KIRINYAGA("Kirinyaga"),
    KISII("Kisii"),
    KISUMU("Kisumu"),
    KITUI("Kitui"),
    KWALE("Kwale"),
    LAIKIPIA("Laikipia"),
    LAMU("Lamu"),
    MACHAKOS("Machakos"),
    MACHEROS("Makueni"),
    MANDERA("Mandera"),
    MERU("Meru"),
    MIGORI("Migori"),
    MOMBASA("Mombasa"),
    MURANGA("Murang'a"),
    NAROK("Narok"),
    NYAMIRA("Nyamira"),
    NYERI("Nyeri"),
    NORTH_EAST("North East"),
    NYANDARUA("Nyandarua"),
    SAMBURU("Samburu"),
    SIAYA("Siaya"),
    TANA_RIVER("Tana River"),
    THARAKA_NITHI("Tharaka Nithi"),
    TRANS_NZOIA("Trans Nzoia"),
    TURKANA("Turkana"),
    UASIN_GISHU("Uasin Gishu"),
    VIHIGA("Vihiga"),
    WAJIR("Wajir"),
    WEST_POKOT("West Pokot");

    private final String prettyName;
}
