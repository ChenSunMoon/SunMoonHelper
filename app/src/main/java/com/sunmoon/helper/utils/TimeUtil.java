package com.sunmoon.helper.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by SunMoon on 2017/2/12.
 */

public class TimeUtil {
    public static DateTimeFormatter format = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
    public static String getNow(){
        DateTime dt=new DateTime();
        return dt.toString(format);
    }
    public static  String fromNow(String d) {
        DateTime time = new DateTime(d);
        return fromNow(time.toDate());
    }
    public static String fromNow(Date d){
        long delta = (new Date().getTime()-d.getTime())/1000;
        if(delta<=0)return d.toLocaleString();
        if(delta/(60*60*24*365) > 0) return delta/(60*60*24*365) +"年前";
        if(delta/(60*60*24*30) > 0) return delta/(60*60*24*30) +"个月前";
        if(delta/(60*60*24*7) > 0)return delta/(60*60*24*7) +"周前";
        if(delta/(60*60*24) > 0) return delta/(60*60*24) +"天前";
        if(delta/(60*60) > 0)return delta/(60*60) +"小时前";
        if(delta/(60) > 0)return delta/(60) +"分钟前";
        return "刚刚";
    }
}
