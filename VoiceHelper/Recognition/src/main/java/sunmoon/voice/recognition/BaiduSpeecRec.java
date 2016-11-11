package sunmoon.voice.recognition;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.baidu.speech.VoiceRecognitionService;

/**
 * Created by SunMoon on 2016/10/24.
 */

public class BaiduSpeecRec implements SpeechRecognitionBuilder {
    private SpeechRecognizer mSpeechRecognizer;
    public BaiduSpeecRec (Context context){
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context, new ComponentName(context,VoiceRecognitionService.class));
    }
    @Override
    public void callBack(RecognitionListener recognitionListener) {
        mSpeechRecognizer.setRecognitionListener(recognitionListener);
    }

    @Override
    public void start(Intent intent) {
        mSpeechRecognizer.startListening(intent);
    }

    @Override
    public void startDialogRec(Activity activity,Intent intent,int flag) {
        intent.setAction("com.baidu.action.RECOGNIZE_SPEECH");
        activity.startActivityForResult(intent,flag);
    }

    @Override
    public void stop() {
        mSpeechRecognizer.stopListening();
    }
}
