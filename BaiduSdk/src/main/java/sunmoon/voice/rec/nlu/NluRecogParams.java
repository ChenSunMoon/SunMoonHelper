package sunmoon.voice.rec.nlu;

import android.app.Activity;
import android.content.SharedPreferences;


import com.baidu.speech.asr.SpeechConstant;

import java.util.Arrays;
import java.util.Map;

import sunmoon.voice.rec.CommonRecogParams;
import sunmoon.voice.rec.PidBuilder;
import sunmoon.voice.rec.offline.OfflineRecogParams;

/**
 * Created by fujiayi on 2017/6/24.
 */

public class NluRecogParams extends CommonRecogParams {


    private static final String TAG = "NluRecogParams";

    public NluRecogParams(Activity context) {
        super(context);
        stringParams.addAll(Arrays.asList(SpeechConstant.NLU));

        intParams.addAll(Arrays.asList(SpeechConstant.DECODER, SpeechConstant.PROP));

        boolParams.addAll(Arrays.asList("_nlu_online"));

        // copyOfflineResource(context);
    }

    public Map<String, Object> fetch(SharedPreferences sp) {

        Map<String, Object> map = super.fetch(sp);
        if (sp.getBoolean("_grammar", false)) {
            Map<String, Object> offlineParams = OfflineRecogParams.fetchOfflineParams();
            map.put(SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH,
                    offlineParams.get(SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH));

        }
        if (sp.getBoolean("_slot_data", false)){
            map.putAll(OfflineRecogParams.fetchSlotDataParam());
        }
        if (sp.getBoolean("_nlu_online",false )){
            map.put("_model","search");
        }
        PidBuilder builder = new PidBuilder();
        map = builder.addPidInfo(map);

        //boolean isOfflineEnabled = sp.getBoolean(SpeechConstant.DECODER, false);
        //map.put(SpeechConstant.DECODER, 0);


        /*if ("sp.getString(SpeechConstant.DECODER, "0")){
            // 离线需要额外设置离线资源文件

        }*/
        return map;

    }


}
