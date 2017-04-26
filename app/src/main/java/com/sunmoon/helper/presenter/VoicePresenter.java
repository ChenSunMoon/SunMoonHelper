package com.sunmoon.helper.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognitionListener;

import com.sunmoon.helper.api.ApiManage;
import com.sunmoon.helper.common.Flag;
import com.sunmoon.helper.model.Message;
import com.sunmoon.helper.callBack.CallBack;
import com.sunmoon.helper.model.PhoneInfo;
import com.sunmoon.helper.model.TuLing;
import com.sunmoon.helper.model.UserCommand;
import com.sunmoon.helper.utils.Apk;
import com.sunmoon.helper.utils.BaiduUntil;
import com.sunmoon.helper.utils.Phone;
import com.sunmoon.helper.utils.UserPurpose;
import com.sunmoon.helper.view.ChatView;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import sunmoon.voice.recognition.SpeechOcr;


/**
 * Created by SunMoon on 2016/11/30.
 */

public class VoicePresenter  extends  Presenter implements RecognitionListener ,CallBack{
    private UserPurpose userPurpose;
    private ChatView view;
    private List<PackageInfo> packageInfos;
    private List<PhoneInfo> phoneInfos;
    private Context context;
    public VoicePresenter(Context context){
        this.userPurpose=new UserPurpose();
        this.context = context;
    }
    public void start(){
        SpeechOcr.callBack(this);
        SpeechOcr.start();
    }

    public void setView(ChatView view) {
        this.view = view;
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {
      view.onRmsChanged(v);
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
        sendRightMsg(result);
        handleResult(userPurpose.getUserPurpose(result));
    }
   public void sendLeftMsg(String msg){
       view.sendMsg(new Message(msg,0));
   }
   public void sendRightMsg(String msg){
       view.sendMsg(new Message(msg,1));
   }
    public  Subscription rx;
    private void handleResult(UserCommand userPurpose) {
        switch (userPurpose.getCommand()){
            case UserCommand.COMMAND_CALLPHONE:
                if (phoneInfos == null || phoneInfos.size() == 0) {
                    phoneInfos=Phone.getPhoneInfos(context);
                }
                List<PhoneInfo> result= Phone.getPhoneByName(phoneInfos,userPurpose.getContent());
                if (result.size()>0){
                  sendLeftMsg("正在您呼叫: " + result.get(0).getName());
                    Phone.callPerson(context,result.get(0).getNumber());
                } else{
                    sendRightMsg("未找到联系人:" + userPurpose.getContent());
                }

                break;
            case UserCommand.COMMAND_OPEN_APP:
                if (packageInfos ==null || packageInfos.size() == 0) {
                    packageInfos=Apk.getPackgeInfos(context);
                }
                String packName= Apk.getAppPackageName(context,packageInfos,userPurpose.getContent());
                if (!packName.equals(Flag.FAIL)){
                    sendRightMsg("正在打开："+userPurpose.getContent());
                    Apk.openApp(context,packName);

                }else {
                    sendLeftMsg("未找到应用："+ userPurpose.getContent());
                }
                break;
            case UserCommand.COMMAND_DELETE_APP:
                if (packageInfos ==null || packageInfos.size() == 0) {
                    packageInfos=Apk.getPackgeInfos(context);
                }
                packName=Apk.getAppPackageName(context,packageInfos,userPurpose.getContent());
                if (!packName.equals(Flag.FAIL)){
                    sendLeftMsg("正在卸载："+userPurpose.getContent());
                    Apk.unInstallApp(context,packName);

                }else {
                    sendLeftMsg("未找到应用："+ userPurpose.getContent());
                }
                break;
            case UserCommand.COMMAND_SEARCH:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.baidu.com/s?wd=" + userPurpose.getContent());
                intent.setData(uri);
                context.startActivity(intent);
                sendLeftMsg("正在搜索："+userPurpose.getContent());
                break;
            case UserCommand.COMMAND_TULING:
               rx = ApiManage.getInstence().getTuLingService().getResult("962899116978d3f7465cabc86286d99d",userPurpose.getContent())
                       .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<TuLing>() {
                            @Override
                            public void call(TuLing tuLing) {
                                sendLeftMsg(tuLing.getText());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                                sendLeftMsg(throwable.getMessage());
                            }
                        });
                break;
        }
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    @Override
    public void success(String result) {
        view.receiveMsg(new Message(result,0));
    }

    @Override
    public void fail(String result) {
        view.receiveMsg(new Message(result,0));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!rx.isUnsubscribed()){
            rx.unsubscribe();
        }

    }
}
