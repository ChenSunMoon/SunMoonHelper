package com.sunmoon.helper;

import android.app.Application;
import android.preference.PreferenceManager;

import com.blankj.utilcode.util.Utils;

import java.util.Map;

import sunmoon.voice.control.SpeechRec;
import sunmoon.voice.control.VoiceWakeup;
import sunmoon.voice.rec.online.OnlineRecogParams;
import com.sunmoon.helper.utils.Speaker;


/**
 * Created by SunMoon on 2016/10/24.
 */

public class App extends Application {
    public static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SpeechRec.init(app);
        OnlineRecogParams onlineRecogParams = new OnlineRecogParams(app);
        Map<String, Object> params = onlineRecogParams.fetch(PreferenceManager.getDefaultSharedPreferences(app));
        SpeechRec.getInstance().setParams(params);
        Speaker.init(this);
        VoiceWakeup.init(this);
        Utils.init(this);
    }
    public static Application get(){
        return app;
    }
}
