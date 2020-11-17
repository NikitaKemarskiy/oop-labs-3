package com.nikita.util;

import java.util.Arrays;

public class ArrayUtil {
    public String[] getStringArrayFilledWith(int length, String fill) {
        String[] arr = new String[length];
        Arrays.fill(arr, fill);
        return arr;
    }
}
