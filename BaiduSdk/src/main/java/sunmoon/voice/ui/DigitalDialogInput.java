package sunmoon.voice.ui;



import java.util.Map;

import sunmoon.voice.control.SpeechRec;
import sunmoon.voice.rec.ChainRecogListener;

/**
 * Created by fujiayi on 2017/10/18.
 */

public class DigitalDialogInput {
    private SpeechRec myRecognizer;

    private ChainRecogListener listener;

    private int code;

    private Map<String, Object> startParams;

    public DigitalDialogInput(SpeechRec myRecognizer, ChainRecogListener listener, Map<String, Object> startParams) {
        this.myRecognizer = myRecognizer;
        this.listener = listener;
        this.startParams = startParams;
    }

    public SpeechRec getMyRecognizer() {
        return myRecognizer;
    }

    public ChainRecogListener getListener() {
        return listener;
    }

    public Map<String, Object> getStartParams() {
        return startParams;
    }
}
