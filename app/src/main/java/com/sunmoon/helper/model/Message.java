package com.sunmoon.helper.model;

/**
 * Created by SunMoon on 2016/11/13.
 */

public class Message {

    private String time;
    private String content;
    private int type;// 0为左侧，1为右侧

    public Message(String content) {
        this.content = content;
    }

    public Message(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
