package sunmoon.voice.recognition;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import com.baidu.speech.VoiceRecognitionService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SunMoon on 2016/10/19.
 */

public class VoiceRec {
    private static VoiceRec instance;
    private static SpeechRecognizer mSpeechRecognizer;
    public  static Intent mIntent;
    public static VoiceRec getInstance(){
            if (instance==null){
                instance=new VoiceRec();
            }
        return instance;
    }

    public void init(Context context){
        mIntent = initConfig();
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context, new ComponentName(context,VoiceRecognitionService.class));
    }
    public static void setRecognitionListener(RecognitionListener listener){
        mSpeechRecognizer.setRecognitionListener(listener);
    }
    public static void startRec(){
        mSpeechRecognizer.startListening(mIntent);
    }
    /**
     *  获取打开语音识别窗口的Intent
     * */
    public static Intent getDialogIntent(){
        mIntent.setAction("com.baidu.action.RECOGNIZE_SPEECH");
        return mIntent;
    }
    public static void stopRec(){
        mSpeechRecognizer.stopListening();
    }
    private Intent initConfig() {
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
}