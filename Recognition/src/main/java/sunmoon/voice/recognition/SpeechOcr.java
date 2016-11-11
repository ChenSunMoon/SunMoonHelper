package sunmoon.voice.recognition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import java.io.File;

/**
 * Created by SunMoon on 2016/10/19.
 */

public class SpeechOcr {
    private static SpeechOcr instance;
    private static SpeechRecognitionBuilder mSpeecRecognition;
    public static final String RESULT= SpeechRecognizer.RESULTS_RECOGNITION;
    public static Intent mIntent;
    public static SpeechOcr getInstance(){
            if (instance==null){
                instance=new SpeechOcr();
            }
        return instance;
    }

    public void init(VoiceConfig voiceConfig){
    }
    public void init(Context context){
        mIntent=getParamsIntent();
        mSpeecRecognition=new BaiduSpeecRec(context);
    }
    public static void  callBack(RecognitionListener callBack){
        mSpeecRecognition.callBack(callBack);
    }
    public static void start(){
        mSpeecRecognition.start(mIntent);
    }
    public static void startDialogRec(Activity activity,int flag){
        mSpeecRecognition.startDialogRec(activity,mIntent,flag);
    }
    public static void stop(){
        mSpeecRecognition.stop();
    }
    private Intent getParamsIntent() {
        //设置帐号详情
        /*intent.putExtra(Constant.APP_ID, "8769143");
        intent.putExtra(Constant.API_KEY, "bv7mxKITLI7m1ir97oVzKzHC");
        intent.putExtra(Constant.SECRET_KEY, "f716f97dc8b2fe1dd5fb9c4d4c359b92");*/
        Intent intent=new Intent();
        //设置采样率
        intent.putExtra(Constant.EXTRA_SAMPLE, 16000);
        //设置语种
        intent.putExtra(Constant.EXTRA_LANGUAGE, "cmn-Hans-CN");
        // intent.putExtra(Constant.EXTRA_OFFLINE_ASR_BASE_FILE_PATH, "/sdcard/s_1");
        //intent.putExtra(Constant.EXTRA_OFFLINE_LM_RES_FILE_PATH, "/sdcard/s_2_Navi");
        intent.putExtra(Constant.EXTRA_PROP, 20000);
        //设置提示音
        intent.putExtra(Constant.EXTRA_SOUND_START, R.raw.bdspeech_recognition_start);
        intent.putExtra(Constant.EXTRA_SOUND_END, R.raw.bdspeech_speech_end);
        intent.putExtra(Constant.EXTRA_SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
        intent.putExtra(Constant.EXTRA_SOUND_ERROR, R.raw.bdspeech_recognition_error);
        intent.putExtra(Constant.EXTRA_SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
        //语音活动检测
        intent.putExtra(Constant.EXTRA_VAD, "input");
        return intent;
    }
    /**
    * 获取存储目录，若不存在就创建新目录。
    * */
    private String getDirPath(){
        String sdcardPath = Environment.getExternalStorageDirectory().toString();
        String dirPath=sdcardPath + "/" + "baiduTTS";
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dirPath;
    }
}
