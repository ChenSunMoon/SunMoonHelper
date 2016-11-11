package sunmoon.voice.recognition;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognitionListener;

/**
 * Created by SunMoon on 2016/10/24.
 */

public interface SpeechRecognitionBuilder{
    public void callBack(RecognitionListener recognitionListener);
    public void start(Intent intent);
    public void startDialogRec(Activity activity,Intent intent,int flag);
    public void stop();
}
