package com.nikita.model.util;

import com.nikita.model.enums.SortType;

public class SortUtil {
    public SortType getSortType(String sortAttributeParam) {
        return sortAttributeParam.startsWith("-")
            ? SortType.DESC
            : SortType.ASC;
    }

    public String getSortAttribute(String sortAttributeParam) {
        return sortAttributeParam.startsWith("-")
            ? sortAttributeParam.substring(1)
            : sortAttributeParam;
    }
}
