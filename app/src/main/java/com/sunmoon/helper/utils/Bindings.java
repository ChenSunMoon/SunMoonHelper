package com.sunmoon.helper.utils;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by SunMoon on 2016/12/19.
 */

public class Bindings {
   @BindingAdapter("img")
    public static void setImg(ImageView view, @DrawableRes int img){
       view.setImageResource(img);
   }
   @BindingAdapter("onLongClick")
    public static void setOnLongClick(View view, View.OnLongClickListener listener){
       view.setOnLongClickListener(listener);
   }
}
