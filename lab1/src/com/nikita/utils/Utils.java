package com.nikita.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private Utils() {}

    public static String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate now = LocalDate.now();
        return dateTimeFormatter.format(now);
    }

    public static String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss-SS:AAAA");
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }
}