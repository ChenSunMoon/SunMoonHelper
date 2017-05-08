package sunmoon.voice.recognition;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.AndroidRuntimeException;
import android.util.Log;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.VoiceRecognitionService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by SunMoon on 2016/10/19.
 */

public class SpeechOcr {
    private static SpeechOcr instance;
    public  static final String RESULT= SpeechRecognizer.RESULTS_RECOGNITION;
    private static SpeechRecognizer mSpeechRecognizer;
    private static EventManager eventManager;
    public  static Intent mIntent;
    public static SpeechOcr getInstance(){
            if (instance==null){
                instance=new SpeechOcr();
            }
        return instance;
    }

    public void init(Context context){
        mIntent= initConfigIntent();
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context, new ComponentName(context,VoiceRecognitionService.class));
        eventManager = EventManagerFactory.create(context, "wp");
    }
    public static void setRecognitionListener(RecognitionListener listener){
        mSpeechRecognizer.setRecognitionListener(listener);
    }
    public static void startRec(){
        mSpeechRecognizer.startListening(mIntent);
    }
    public static void startDialogRec(Activity activity,int flag){
        activity.startActivityForResult(mIntent,flag);
    }
    public static void stopRec(){
        mSpeechRecognizer.stopListening();
    }
    private Intent initConfigIntent() {
        //设置帐号详情
        /*intent.putExtra(Constant.APP_ID, "8769143");
        intent.putExtra(Constant.API_KEY, "bv7mxKITLI7m1ir97oVzKzHC");
        intent.putExtra(Constant.SECRET_KEY, "f716f97dc8b2fe1dd5fb9c4d4c359b92");*/
        Intent intent=new Intent();
        //设置采样率
        intent.putExtra(Constant.EXTRA_SAMPLE, 16000);
        //设置语种
        intent.putExtra(Constant.EXTRA_LANGUAGE, "cmn-Hans-CN");
        //intent.putExtra(Constant.EXTRA_OFFLINE_ASR_BASE_FILE_PATH, "/sdcard/baidu/s_1");
        intent.putExtra("grammar", "assets:///baidu_speech_grammar.bsg");
        intent.putExtra(Constant.EXTRA_PROP, 20000);
        //设置提示音
        intent.putExtra(Constant.EXTRA_SOUND_START, R.raw.bdspeech_recognition_start);
        intent.putExtra(Constant.EXTRA_SOUND_END, R.raw.bdspeech_speech_end);
        intent.putExtra(Constant.EXTRA_SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
        intent.putExtra(Constant.EXTRA_SOUND_ERROR, R.raw.bdspeech_recognition_error);
        intent.putExtra(Constant.EXTRA_SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
        // 语音活动检测
        intent.putExtra(Constant.EXTRA_VAD, "input");
        // 离线垂类槽数据设置
        JSONObject slotData = new JSONObject();
        JSONArray names = new JSONArray();
        try {
            slotData.put("name",names);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        intent.putExtra("slot-data", slotData.toString());
        return intent;
    }
    public static void registerWakeUpListener(EventListener listener){
        eventManager.registerListener(listener);
    }
    public static void startWakeUp(){
        HashMap params = new HashMap();
        params.put("kws-file", "assets:///MyWakeUp.bin");
        eventManager.send("wp.start", new JSONObject(params).toString(), null, 0, 0);
    }
    public static void stopWakeUp(){
        eventManager.send("wp.stop", null, null, 0, 0);
    }
    public static void stopAll(){
        stopWakeUp();
        stopRec();
    }
}