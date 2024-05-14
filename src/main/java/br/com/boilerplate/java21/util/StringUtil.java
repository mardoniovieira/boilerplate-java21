package br.com.boilerplate.java21.util;

import io.micrometer.common.util.StringUtils;

public class StringUtil {

    public static String getNonNullString(String string) {
        return StringUtils.isBlank(string) ? "" : string;
    }

}
