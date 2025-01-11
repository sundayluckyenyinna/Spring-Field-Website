package com.springfield.website.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GhanaState {
    AHAFO("Ahafo"),
    ASHANTI("Ashanti"),
    BRONG_AHAFO("Brong Ahafo"),
    CENTRAL("Central"),
    EASTERN("Eastern"),
    GREATER_ACCRA("Greater Accra"),
    NORTHERN("Northern"),
    OVRANGO_REGION("Oti"),
    SAVANNAH("Savannah"),
    UPPER_EAST("Upper East"),
    UPPER_WEST("Upper West"),
    VOLTA("Volta"),
    WESTERN("Western"),
    WESTERN_NORTH("Western North");
    private final String prettyName;
}
