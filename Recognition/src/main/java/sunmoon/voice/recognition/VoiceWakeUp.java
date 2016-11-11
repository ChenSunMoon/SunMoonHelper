package sunmoon.voice.recognition;

import android.content.Context;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by SunMoon on 2016/10/24.
 */

public class VoiceWakeUp {
   private EventManager mWakeUpManger;
    private final String START_CMD="wp.start";
    private final String STOP_CMD="wp.stop";
    private HashMap params;
    public VoiceWakeUp(Context context){
        mWakeUpManger = EventManagerFactory.create(context, "wp");
        initWakeUp();
    }

    public void initWakeUp() {
        // 设置唤醒资源, 唤醒资源请到 http://yuyin.baidu.com/wake#m4 来评估和导出
        params = new HashMap();
        params.put("kws-file", "assets:///WakeUp.bin");
    }
    public void start(){
        mWakeUpManger.send(START_CMD, new JSONObject(params).toString(), null, 0, 0);
    }
    public void stop(){
        mWakeUpManger.send(STOP_CMD, null, null, 0, 0);
    }
    public void callBack(EventListener ev){
        mWakeUpManger.registerListener(ev);
    }
}
