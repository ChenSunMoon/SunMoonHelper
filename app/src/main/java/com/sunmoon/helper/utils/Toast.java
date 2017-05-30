package com.sunmoon.helper.utils;

import android.content.Context;

/**
 * Created by SunMoon on 2017/5/29.
 */

public class Toast {
    public static void  show(Context context, String content){
        android.widget.Toast.makeText(context,content, android.widget.Toast.LENGTH_SHORT).show();
    }
}
