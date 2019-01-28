package com.vastenly.taf.util;


public class StringUtils {

    public static String replaceLast(String str, String regex, String replacement) {
        return str.replaceAll("[" + regex + "$]", replacement);
    }

}
