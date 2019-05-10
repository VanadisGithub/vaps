package com.vanadis.vap.utils.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yaoyuan
 */
public class RegexUtils {

    private static String HOST_PATTERN = "(http://|https://)?([^/]*)";

    private static String ID_PATTERN = "[?&]id=(\\d+)";

    public static String getHost(String url) {
        Pattern p = Pattern.compile(HOST_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url);
        return m.find() ? m.group(2) : url;
    }

    public static String getId(String url) {
        Pattern p = Pattern.compile(ID_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(url);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * 正则匹配
     *
     * @param soap
     * @param regx
     * @return
     */
    public static String getSubUtilSimple(String soap, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * Unicode转中文
     *
     * @param dataStr
     * @return
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    /**
     * 获取随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String keyString = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer sb = new StringBuffer();
        int len = keyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(keyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    /**
     * 获取16进制随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString16(int length) {
        String keyString = "abcdef0123456789";
        StringBuffer sb = new StringBuffer();
        int len = keyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(keyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }
}
