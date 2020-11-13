package com.nikita.util;

public class ArrayUtil {
    public static int[] getIntArray(Integer[] arr) {
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = arr[i];
        }
        return intArr;
    }
}
