package com.sunmoon.helper.view;

import com.sunmoon.helper.model.Message;

/**
 * Created by SunMoon on 2017/4/9.
 */

public interface ChatView extends View {
    public void sendMsg(Message message);
    public void receiveMsg(Message message);
    public void onRmsChanged(float v);
}
