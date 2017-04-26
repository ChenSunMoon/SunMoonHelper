package com.sunmoon.helper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SunMoon on 2016/11/14.
 */

public class TuLing {
    @SerializedName("code")
    private String code;
    @SerializedName("text")
    private String text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
