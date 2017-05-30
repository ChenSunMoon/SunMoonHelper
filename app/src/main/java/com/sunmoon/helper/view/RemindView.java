package com.sunmoon.helper.view;

import com.sunmoon.helper.model.Remind;

/**
 * Created by SunMoon on 2017/5/16.
 */

public interface RemindView extends View {
    void setTime(int hour, int min);
    void back();
    int getHourOfDay();
    int getMinuteOfHour();

}
