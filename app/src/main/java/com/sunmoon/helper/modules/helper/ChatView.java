package com.sunmoon.helper.modules.helper;

import android.content.Intent;

/**
 * Created by SunMoon on 2017/4/9.
 */

public interface ChatView {
    public void onRmsChanged(float v);
    public void smoothBottom();
    void onGetDialogIntent(Intent intent);
}


