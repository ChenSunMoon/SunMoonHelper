package com.sunmoon.helper.view;

import com.sunmoon.helper.model.Remind;

import java.util.List;

/**
 * Created by SunMoon on 2017/5/23.
 */

public interface MyRemindView extends View{
    void iniReminds(List<Remind> reminds);

    void deleteRemind(int i);

    void updateRemind(Remind item, int i);
}
