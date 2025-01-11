package com.springfield.website.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CanadaState {
    ALBERTA("Alberta"),
    BRITISH_COLUMBIA("British Columbia"),
    MANITOBA("Manitoba"),
    NEW_BRUNSWICK("New Brunswick"),
    NEWFOUNDLAND_AND_LABRADOR("Newfoundland and Labrador"),
    NORTHWEST_TERRITORIES("Northwest Territories"),
    NOVA_SCOTIA("Nova Scotia"),
    ONTARIO("Ontario"),
    PRINCE_EDWARD_ISLAND("Prince Edward Island"),
    QUEBEC("Quebec"),
    SASKATCHEWAN("Saskatchewan"),
    YUKON("Yukon"),
    NUNAVUT("Nunavut");
    private final String prettyName;
}
