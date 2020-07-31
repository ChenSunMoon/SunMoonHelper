package com.sunmoon.helper.utils;

import android.content.Context;
import android.util.Log;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;


/**
 * Created by SunMoon on 2017/5/3.
 */

public class    Speaker {
    public  SpeechSynthesizer mSpeechSynthesizer;
    public  boolean isSpeaking;
    public static Speaker instance;
    public static void init(Context context) {
        instance = new Speaker(context);
        instance.setListener(new SpeechSynthesizerListener() {
            @Override
            public void onSynthesizeStart(String s) {

            }

            @Override
            public void onSynthesizeDataArrived(String s, byte[] bytes, int i, int i1) {

            }

            @Override
            public void onSynthesizeFinish(String s) {

            }

            @Override
            public void onSpeechStart(String s) {

            }

            @Override
            public void onSpeechProgressChanged(String s, int i) {

            }

            @Override
            public void onSpeechFinish(String s) {

            }

            @Override
            public void onError(String s, SpeechError speechError) {
                Log.e("语音合成错误", ""+speechError.description);
            }
        });
    }
    public static Speaker getInstance() {
        if (instance == null) throw new IllegalStateException("speaker instance is null");
        return  instance;
    }
    public  Speaker(Context context){
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(context);
        mSpeechSynthesizer.setAppId("8769143");
        mSpeechSynthesizer.setApiKey("bv7mxKITLI7m1ir97oVzKzHC", "f716f97dc8b2fe1dd5fb9c4d4c359b92");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        mSpeechSynthesizer.auth(TtsMode.ONLINE);
        // 初始化tts
        int code = mSpeechSynthesizer.initTts(TtsMode.ONLINE);
        Log.i("语音合成:", ""+code);
    }
    public  void setListener(SpeechSynthesizerListener listener){
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);
    }
    public  void start(String text){
        if (isSpeaking){
            mSpeechSynthesizer.stop();
            mSpeechSynthesizer.speak(text);
        } else {
            mSpeechSynthesizer.speak(text);
        }


    }
    public void stop(){
        mSpeechSynthesizer.stop();
    }
    public void release() {
        mSpeechSynthesizer.release();
    }
}
