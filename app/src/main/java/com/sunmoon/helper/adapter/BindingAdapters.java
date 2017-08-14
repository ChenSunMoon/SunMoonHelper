package com.sunmoon.helper.adapter;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TimePicker;

import retrofit2.http.Url;

/**mport android.widget.TimePicker;

 * Created by SunMoon on 2017/8/7.
 */

public class BindingAdapters {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @BindingAdapter("hour")
    public static void bindHour(TimePicker tp, int hour) {
        tp.setHour(hour);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @BindingAdapter("minute")
    public static void bindMinute(TimePicker tp, int minute) {
        tp.setMinute(minute);
    }
    @BindingAdapter("img")
    public static void setImg(ImageView view, @DrawableRes int img){
        view.setImageResource(img);
    }
    @BindingAdapter("onLongClick")
    public static void setOnLongClick(View view, View.OnLongClickListener listener){
        view.setOnLongClickListener(listener);
    }
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView rv, RecyclerView.Adapter adapter){
        rv.setAdapter(adapter);
    }
    @BindingAdapter("url")
    public static void setUrl(WebView wb, String url){
        wb.loadUrl(url);
    }
}
