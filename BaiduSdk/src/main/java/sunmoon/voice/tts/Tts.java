package sunmoon.voice.tts;

import android.content.Context;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;


/**
 * Created by SunMoon on 2017/5/3.
 */

public class Tts implements SpeechSynthesizerListener {
    public  SpeechSynthesizer speechRecognizer;
    public  boolean isSpeaking;
    public  void init(Context context){
        speechRecognizer= SpeechSynthesizer.getInstance();
        speechRecognizer.setContext(context);
        // 文本模型文件路径 (离线引擎使用)
        // 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
        speechRecognizer.setAppId("8769143"/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
        // 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
        speechRecognizer.setApiKey("bv7mxKITLI7m1ir97oVzKzHC",
                "f716f97dc8b2fe1dd5fb9c4d4c359b92"/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
        speechRecognizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置Mix模式的合成策略
        speechRecognizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
        // AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
        AuthInfo authInfo = speechRecognizer.auth(TtsMode.MIX);
        // 初始化tts
        speechRecognizer.initTts(TtsMode.MIX);
        speechRecognizer.setSpeechSynthesizerListener(this);
    }
    public  void speak(String text){
        if (isSpeaking){
            speechRecognizer.stop();
            speechRecognizer.speak(text);
        } else {
            speechRecognizer.speak(text);
        }

    }
    public void stop(){
        speechRecognizer.stop();
    }

    @Override
    public void onSynthesizeStart(String s) {

    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {

    }

    @Override
    public void onSynthesizeFinish(String s) {

    }

    @Override
    public void onSpeechStart(String s) {
        isSpeaking = true;
    }

    @Override
    public void onSpeechProgressChanged(String s, int i) {

    }

    @Override
    public void onSpeechFinish(String s) {
        isSpeaking = false;
    }

    @Override
    public void onError(String s, SpeechError speechError) {

    }
}
