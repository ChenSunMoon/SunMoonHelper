package sunmoon.voice.rec.online;

import android.app.Activity;



import java.io.IOException;
import java.io.InputStream;

import sunmoon.voice.util.Logger;

/**
 * Created by fujiayi on 2017/6/20.
 */

public class InFileStream {

    private static Activity context;

    private static final String TAG = "InFileStream";
    public static void setContext(Activity context){
        InFileStream.context = context;
    }

    public static InputStream create16kStream(){
        InputStream is = null;
        Logger.info(TAG,"cmethod call");
        try {
            is = context.getAssets().open("outfile.pcm");
            Logger.info(TAG,"create input stream ok" + is.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 此处仅为演示，实际情况不能一下子塞入过长音频，建议最快0.5s读取1s长度的音频
        return is;
    }
}