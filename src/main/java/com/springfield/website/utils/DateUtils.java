package com.springfield.website.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final String DEFAULT_AFRICA_LAGOS_ZONE_ID = "Africa/Lagos";
    public static final ZoneId DEFAULT_AFRICA_LAGOS_ZONE = ZoneId.of(DEFAULT_AFRICA_LAGOS_ZONE_ID);
    public static final String HYPHENATED_LOCAL_DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDateTime getCurrentDateTime(){
        return LocalDateTime.now(DEFAULT_AFRICA_LAGOS_ZONE);
    }

    public static LocalDateTime getCurrentDateTime(String zone){
        return LocalDateTime.now(ZoneId.of(zone));
    }

    public static LocalDateTime getCurrentDateTime(ZoneId zoneId){
        return LocalDateTime.now(zoneId);
    }

    public static LocalDate getCurrentDate(){
        return LocalDate.now();
    }

    public static LocalDate getCurrentDate(String zone){
        return LocalDate.now(ZoneId.of(zone));
    }

    public static LocalDate getCurrentDate(ZoneId zoneId){
        return LocalDate.now(zoneId);
    }

    public static String formatDate(LocalDateTime localDateTime, DateTimeFormatter formatter){
        return formatter.format(localDateTime);
    }

    public static String formatDate(LocalDateTime localDateTime, String pattern){
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    public static String formatDate(LocalDate localDate, DateTimeFormatter formatter){
        return formatter.format(localDate);
    }

    public static String formatDate(LocalDate localDate, String pattern){
        return DateTimeFormatter.ofPattern(pattern).format(localDate);
    }

    public static LocalDateTime fromDateTimeStringAndPattern(String dateString, String pattern){
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate fromDateStringAndPattern(String dateString, String pattern){
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(pattern));
    }
}
