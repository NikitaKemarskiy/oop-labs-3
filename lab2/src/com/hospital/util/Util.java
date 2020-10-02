package com.hospital.util;

import java.util.*;

public class Util {
    private Util() {}

    private static int getRandomInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static Date getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        int year = getRandomInt(1940, 2000);
        int month = getRandomInt(0, 11);
        int day = getRandomInt(1, 28);
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static <T, V> Map<T, V> listToMapWithDefaultValue(List<T> list, V defaultValue) {
        Map<T, V> map = new HashMap<>();
        for (T item : list) {
            map.put(item, defaultValue);
        }
        return map;
    }
}
