package com.sunmoon.helper.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * Created by SunMoon on 2016/11/6.
 */

public class BaseActivity extends AppCompatActivity {
    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
