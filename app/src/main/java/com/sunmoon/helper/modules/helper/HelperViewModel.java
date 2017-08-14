package com.sunmoon.helper.modules.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;

import com.sunmoon.helper.api.ApiManage;
import com.sunmoon.helper.base.BaseViewModel;
import com.sunmoon.helper.common.Flag;
import com.sunmoon.helper.modules.remind.EditRemindActivity;
import com.sunmoon.helper.modules.search.SearchActivity;
import com.sunmoon.helper.model.Message;
import com.sunmoon.helper.model.Phone;
import com.sunmoon.helper.model.TuLing;
import com.sunmoon.helper.model.UserCommand;
import com.sunmoon.helper.utils.Apk;

import sunmoon.voice.recognition.BaiduUtil;

import com.sunmoon.helper.utils.PhoneUtil;
import com.sunmoon.helper.utils.StringUtil;
import com.sunmoon.helper.utils.UserPurpose;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import sunmoon.voice.recognition.SpeechOcr;
import sunmoon.voice.recognition.VoiceWakeUp;
import sunmoon.voice.recognition.WakeUpListener;
import sunmoon.voice.tts.Tts;


/**
 * Created by SunMoon on 2016/11/30.
 */

@TargetApi(Build.VERSION_CODES.FROYO)
public class HelperViewModel extends BaseViewModel implements RecognitionListener  {
    private UserPurpose userPurpose;
    private VoiceAdapter adapter;
    private List<PackageInfo> packageInfos;
    private List<Phone> phoneInfos;
    private Context context;
    private Tts tts = new Tts();
    private VoiceWakeUp wakeUp;
    private final int REC_CODE = 1;// 语音识别
    private ChatView v;
    public HelperViewModel(final Context context) {
        this.userPurpose = new UserPurpose();
        this.context = context;
        adapter = new VoiceAdapter(context);
        tts.init(context);
        wakeUp = new VoiceWakeUp(context);
        wakeUp.registerListener(new WakeUpListener() {
            @Override
            public void success() {
                startRecEvent();
            }

            @Override
            public void exit() {

            }
        });
        SpeechOcr.setRecognitionListener(this);
    }
    public void setView(ChatView v){
        this.v = v;
    }
    public VoiceAdapter getAdapter() {
        return adapter;
    }

    public void startWakeUp() {
        wakeUp.start();
    }

    public void startRecEvent() {
        tts.stop();
        wakeUp.stop();
        v.startRecDialog(SpeechOcr.startDialogRec(), REC_CODE);
    }

    public void stopAll() {
        SpeechOcr.stopAll();
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {
        this.v.onRmsChanged(v);
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
        String raw = BaiduUtil.getRecResult(bundle);
        String result = StringUtil.ridPunctuation(raw);
        sendRightMsg(result);
        handleResult(userPurpose.getUserCommand(result));

    }

    public void sendLeftMsg(String msg, boolean isSpeak) {
        Message msgs = new Message(msg, 0);
        adapter.addMessage(msgs);
        if (isSpeak) {
            tts.speak(msg);
        }
        v.smoothBottom();
    }

    public void sendRightMsg(String msg) {
        v.smoothBottom();
        adapter.addMessage(new Message(msg, 1));
    }

    public void handleResult(UserCommand userCommand) {
        String target = userCommand.getSentence().getTarget();
        switch (userCommand.getType()) {
            case UserCommand.COMMAND_CALL_PHONE:
                if (phoneInfos == null || phoneInfos.size() == 0) {
                    phoneInfos = PhoneUtil.getPhoneInfos(context);
                }
                List<Phone> result = PhoneUtil.getPhonesByName(phoneInfos, target);
                if (result.size() > 0) {
                    sendLeftMsg("正在为您呼叫: " + result.get(0).getName(), false);
                    PhoneUtil.fastCallPerson(context, result.get(0).getNumber());
                } else {
                    sendLeftMsg("未找到联系人:" + target, true);
                }
                break;
            case UserCommand.COMMAND_OPEN_APP:
                if (packageInfos == null || packageInfos.size() == 0) {
                    packageInfos = Apk.getPackagesInfos(context);
                }
                String packName = Apk.getAppPackageName(context, packageInfos, target);
                if (!packName.equals(Flag.FAIL)) {
                    sendLeftMsg("正在打开：" + target, false);
                    Apk.openApp(context, packName);
                } else {
                    sendLeftMsg("未找到应用：" + target, true);
                }
                break;
            case UserCommand.COMMAND_DELETE_APP:
                if (packageInfos == null || packageInfos.size() == 0) {
                    packageInfos = Apk.getPackagesInfos(context);
                }
                packName = Apk.getAppPackageName(context, packageInfos, target);
                if (!packName.equals(Flag.FAIL)) {
                    sendLeftMsg("正在卸载：" + target, false);
                    Apk.unInstallApp(context, packName);

                } else {
                    sendLeftMsg("未找到应用：" + target, true);
                }
                break;
            case UserCommand.COMMAND_SEARCH:
                Intent intent = new Intent(context,SearchActivity.class);
                intent.putExtra("keyword", target);
                context.startActivity(intent);
                break;
            case UserCommand.COMMAND_REMIND:
                 intent = new Intent(context, EditRemindActivity.class);
                break;
            case UserCommand.COMMAND_CHAT:
                Subscription rx = ApiManage.getInstence().getTuLingService().getResult(com.sunmoon.helper.common.TuLing.API_KEY, target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<TuLing>() {
                            @Override
                            public void call(TuLing tuLing) {
                                sendLeftMsg(tuLing.getText(), true);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                sendLeftMsg(throwable.getMessage(), false);
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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REC_CODE && data != null) {
            onResults(data.getExtras());
        }
    }

}
