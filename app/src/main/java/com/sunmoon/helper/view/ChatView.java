package com.sunmoon.helper.view;

import android.app.Fragment;
import android.content.Intent;

import com.sunmoon.helper.model.Message;

/**
 * Created by SunMoon on 2017/4/9.
 */

public interface ChatView extends View {
    public void onRmsChanged(float v);
    public void smoothBottom();
    void startRecDialog(Intent intent,int rec_code);
}


