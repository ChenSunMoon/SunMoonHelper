package com.sunmoon.helper.utils;

/**
 * Created by SunMoon on 2017/4/30.
 */

public class StringUtil {
    public static String ridPunctuation(String content){
        return content.replaceAll(" ", "").replace("\n", "").replace(",", "").replace("，", "").replace("。", "").toUpperCase();
    }
}
