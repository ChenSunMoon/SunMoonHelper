package sunmoon.com.helper.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.sunmoon.sunui.VoiceView;

import java.util.ArrayList;
import java.util.Arrays;

import sunmoon.com.helper.R;
import sunmoon.com.helper.databinding.ActivityVoiceBinding;
import sunmoon.voice.recognition.SpeechOcr;

/**
 * Created by SunMoon on 2016/11/6.
 */

public class VoiceActivity extends BaseActivity implements VoiceView.OnClickListener,RecognitionListener{
    private ActivityVoiceBinding b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=DataBindingUtil.setContentView(this, R.layout.activity_voice);
        b.vVoice.setOnClickListener(this);

    }


    @Override
    public void onClick() {
        SpeechOcr.callBack(this);
        SpeechOcr.start();
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {
        b.vVoice.setVolume(v/50);
    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onResults(Bundle bundle) {
        setResultToTextView(bundle);

    }

    @Override
    public void onPartialResults(Bundle bundle) {
        setResultToTextView(bundle);

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
    private void setResultToTextView(Bundle bundle){
        ArrayList<String> nbest =bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String result = Arrays.toString(nbest.toArray(new String[nbest.size()])).replace("[", "").replace("]", "");
        b.tvResult.setText(result);
    }
}
