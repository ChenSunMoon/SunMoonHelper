package com.sunmoon.helper.utils;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SunMoon on 2016/11/18.
 */

public class BaiduUntil  {
    public static String getRecResult(Bundle bundle){
        ArrayList<String> nbest =bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String result = Arrays.toString(nbest.toArray(new String[nbest.size()])).replace("[", "").replace("]", "").replaceAll("。","");
        return result;
    }
}
