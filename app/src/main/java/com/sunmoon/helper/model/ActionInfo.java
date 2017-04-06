package com.sunmoon.helper.model;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * Created by SunMoon on 2016/11/4.
 */

public class ActionInfo {
    private String name;
    private String recentContent;
    private String time;
    private String url;
    private String type="html";
    private Class activity;
    public static final String ACTIVITY="activity";
    public static final String HTML="html";
    private int icon;
    public ActionInfo(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
    }

    public ActionInfo(String name, Class activity,@DrawableRes int icon, String type) {
        this.name = name;
        this.activity = activity;
        this.type = type;
        this.icon=icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecentContent() {
        return recentContent;
    }

    public void setRecentContent(String recentContent) {
        this.recentContent = recentContent;
    }


    public void getPreText(){

    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public void openPage(View view){
        Intent intent=new Intent(view.getContext(),activity);
        view.getContext().startActivity(intent);
    }
}
