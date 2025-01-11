package com.springfield.website.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EnglandStates {
    BEDFORDSHIRE("Bedfordshire"),
    BERKSHIRE("Berkshire"),
    BRISTOL("Bristol"),
    BUCKINGHAMSHIRE("Buckinghamshire"),
    CAMBRIDGESHIRE("Cambridgeshire"),
    CHESHIRE("Cheshire"),
    CITY_OF_LONDON("City of London"),
    CORNWALL("Cornwall"),
    CUMBRIA("Cumbria"),
    DERBYSHIRE("Derbyshire"),
    DEVON("Devon"),
    DORSET("Dorset"),
    DURHAM("Durham"),
    EAST_RIDING_OF_YORKSHIRE("East Riding of Yorkshire"),
    EAST_SUSSEX("East Sussex"),
    ESSEX("Essex"),
    GLOUCESTERSHIRE("Gloucestershire"),
    GREATER_LONDON("Greater London"),
    GREATER_MANCHESTER("Greater Manchester"),
    HAMPSHIRE("Hampshire"),
    HEREFORDSHIRE("Herefordshire"),
    HERTFORDSHIRE("Hertfordshire"),
    ISLE_OF_WIGHT("Isle of Wight"),
    KENT("Kent"),
    LANCASHIRE("Lancashire"),
    LEICESTERSHIRE("Leicestershire"),
    LINCOLNSHIRE("Lincolnshire"),
    MERSEYSIDE("Merseyside"),
    NORFOLK("Norfolk"),
    NORTH_YORKSHIRE("North Yorkshire"),
    NORTHAMPTONSHIRE("Northamptonshire"),
    NORTHUMBERLAND("Northumberland"),
    NOTTINGHAMSHIRE("Nottinghamshire"),
    OXFORDSHIRE("Oxfordshire"),
    RUTLAND("Rutland"),
    SHROPSHIRE("Shropshire"),
    SOMERSET("Somerset"),
    SOUTH_YORKSHIRE("South Yorkshire"),
    STAFFORDSHIRE("Staffordshire"),
    SUFFOLK("Suffolk"),
    SURREY("Surrey"),
    TYNE_AND_WEAR("Tyne and Wear"),
    WARWICKSHIRE("Warwickshire"),
    WEST_MIDLANDS("West Midlands"),
    WEST_SUSSEX("West Sussex"),
    WEST_YORKSHIRE("West Yorkshire"),
    WILTSHIRE("Wiltshire"),
    WORCESTERSHIRE("Worcestershire");
    private final String prettyName;
}
