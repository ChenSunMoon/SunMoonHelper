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
    private WakeUpListener wakeUpListener;
    private Boolean isRecing = false; // 是否已经在监听中
    private EventListener eventListener = new EventListener() {
        @Override
        public void onEvent(String name, String params, byte[] bytes, int i, int i1) {
            if (wakeUpListener != null) {
                if ("wp.data".equals(name)) { // 每次唤醒成功, 将会回调name=wp.data的时间, 被激活的唤醒词在params的word字段
                    wakeUpListener.success();
                    isRecing = false;
                } else if ("wp.exit".equals(name)) {
                    wakeUpListener.exit();
                }
            }

        }
    };
    private HashMap params;
    public VoiceWakeUp(Context context) {
        mWakeUpManger = EventManagerFactory.create(context, "wp");
        params = new HashMap(); // 设置唤醒资源, 唤醒资源请到 http://yuyin.baidu.com/wake#m4 来评估和导出
        params.put("kws-file", "assets:///MyWakeUp.bin");
        mWakeUpManger.registerListener(eventListener);

    }

    public void registerListener(WakeUpListener listener) {
        this.wakeUpListener = listener;
    }

    public void start() {
        if (!isRecing) {
            isRecing = true;
            mWakeUpManger.send("wp.start", new JSONObject(params).toString(), null, 0, 0);
        }
    }

    public void stop() {
        if (isRecing) {
            isRecing  = false;
            mWakeUpManger.send("wp.stop", null, null, 0, 0);
        }
    }

}
