package sunmoon.com.helper;

import android.app.Application;

import sunmoon.voice.recognition.SpeechOcr;

/**
 * Created by SunMoon on 2016/10/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechOcr.getInstance().init(this);
    }
}
