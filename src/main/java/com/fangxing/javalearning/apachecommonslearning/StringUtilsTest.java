package com.fangxing.javalearning.apachecommonslearning;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringUtils的测试类
 */
public class StringUtilsTest {


    /**
     * 字符串截取的测试方法
     */
    @Test
    public void cutTest() {
        String str = "hello wlorld";

        String result = StringUtils.substring(str, 0);
        String result2 = StringUtils.substring(str, 1);
        String result3 = StringUtils.substring(null, 1);
        String result4 = StringUtils.substring("", 1);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);

        String result5 = StringUtils.substringAfter(str, "l");
        String result6 = StringUtils.substringAfterLast(str, "l");
        System.out.println(result5);
        System.out.println(result6);

        String result7 = StringUtils.substringBefore(str, "o");
        String result8 = StringUtils.substringBeforeLast(str, "o");
        System.out.println(result7);
        System.out.println(result8);

        String result9 = StringUtils.substringBetween(str, "o");
        String result10 = StringUtils.substringBetween(str, "l", "d");
        String[] result11 = StringUtils.substringsBetween(str, "l", "l");
        System.out.println(result9);
        System.out.println(result10);
        System.out.println(result11.length);

    }

    /**
     * 去除字符串首尾的控制符（char ≤ 32）
     */
    @Test
    public void whiteSpaceTest() {

        String str = " hello world ";

        String result = StringUtils.trim(str);
        String result2 = StringUtils.trim(null);
        System.out.println(result);
        System.out.println(result2);

        String result3 = StringUtils.trimToEmpty(str);
        String result4 = StringUtils.trimToEmpty(null);
        System.out.println(result3);
        System.out.println(result4);

        String result5 = StringUtils.trimToNull(str);
        String result6 = StringUtils.trimToNull("");
        String result7 = StringUtils.trimToNull(" \t\n\r\nabc    ");
        System.out.println(result5);
        System.out.println(result6);
        System.out.println(result7);

        String result8 = StringUtils.strip(str);
        String result9 = StringUtils.stripToEmpty(str);
        String result10 = StringUtils.stripToNull(str);
        System.out.println(result8);
        System.out.println(result9);
        System.out.println(result10);

        String result11 = StringUtils.deleteWhitespace(str);
        System.out.println(result11);

    }

    /**
     * 字符串连接
     */
    @Test
    public void joinTest() {
        List<String> strList = new ArrayList<>();
        strList.add("hello");
        strList.add("world");
        String result = StringUtils.join(strList, 'a');
        System.out.println(result);

    }
}
