package com.sunmoon.helper.presenter;

import android.content.Context;
import android.os.Bundle;
import android.speech.RecognitionListener;

import com.sunmoon.helper.HandleResult;
import com.sunmoon.helper.adapter.VoiceAdapter;
import com.sunmoon.helper.model.Message;
import com.sunmoon.helper.callBack.CallBack;
import com.sunmoon.helper.databinding.ActivityVoiceBinding;
import com.sunmoon.helper.utils.BaiduUntil;
import com.sunmoon.helper.utils.UserPurpose;

import sunmoon.voice.recognition.SpeechOcr;


/**
 * Created by SunMoon on 2016/11/30.
 */

public class VoicePresenter implements RecognitionListener ,CallBack{
    private Context context;
    private HandleResult handleResult;//处理结果
    private VoiceAdapter voiceAdapter;
    private UserPurpose userPurpose;
    private ActivityVoiceBinding b;
    public VoicePresenter(Context context, VoiceAdapter voiceAdapter, ActivityVoiceBinding  b){
        this.b=b;
        this.context=context;
        this.handleResult=new HandleResult(context);
        this.handleResult.setCallBack(this);
        this.voiceAdapter=voiceAdapter;
        this.userPurpose=new UserPurpose();

    }
    public void start(){
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
        String result= BaiduUntil.getRecResult(bundle);
        voiceAdapter.addMessage(new Message(result,1));
        handleResult.exec(userPurpose.getUserPurpose(result));
        smooothBottom();
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    @Override
    public void success(String result) {
        voiceAdapter.addMessage(new Message(result,0));
        smooothBottom();
    }

    @Override
    public void fail(String res) {
        voiceAdapter.addMessage(new Message(res,0));
        smooothBottom();
    }

    private void smooothBottom(){
        b.rvContent.smoothScrollToPosition(voiceAdapter.getItemCount()-1);
    }

}
