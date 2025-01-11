package com.springfield.website.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SouthAfricaState {
    EASTERN_CAPE("Eastern Cape"),
    FREE_STATE("Free State"),
    GAUTENG("Gauteng"),
    KWAZULU_NATAL("KwaZulu-Natal"),
    LIMPOPO("Limpopo"),
    MPUMALANGA("Mpumalanga"),
    NORTH_WEST("North West"),
    NORTHERN_CAPE("Northern Cape"),
    WESTERN_CAPE("Western Cape");
    private final String prettyName;
}
