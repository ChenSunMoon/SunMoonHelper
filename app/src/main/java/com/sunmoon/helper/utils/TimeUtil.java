package com.sunmoon.helper.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by SunMoon on 2017/2/12.
 */

public class TimeUtil {
    public static DateTimeFormatter format = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
    public static String getNow(){
        DateTime dt=new DateTime();
        return dt.toString(format);
    }
}
