package com.wushunda.injectcode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wsd
 */
public class Util {

    private final static String UNDERSCORE = "_";
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰（属性名）
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        StringBuilder result = new StringBuilder(str);
        if (String.valueOf(result.charAt(0)).equals(UNDERSCORE)) {
            result.deleteCharAt(0);
        }
        if (String.valueOf(result.charAt(result.length() - 1)).equals(UNDERSCORE)) {
            result.deleteCharAt(result.length() - 1);
        }
        Matcher matcher = linePattern.matcher(result);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 首字母大写，下划线转驼峰（类名，方法名）
     *
     * @param str
     * @return
     */
    public static String firstCapitalLineToHump(String str) {
        String className = lineToHump(str);
        return className.substring(0, 1).toUpperCase() + className.substring(1);
    }
}
