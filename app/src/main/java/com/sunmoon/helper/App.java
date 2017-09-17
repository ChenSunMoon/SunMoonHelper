package com.sunmoon.helper;

import android.app.Application;

import sunmoon.voice.recognition.VoiceRec;

/**
 * Created by SunMoon on 2016/10/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化语音识别
        VoiceRec.getInstance().init(this);
    }


}
