package com.sunmoon.helper.model;

/**
 * Created by SunMoon on 2016/11/19.
 */

public class ContactInfo {
    private String name;
    private String phoneNumber;

    public ContactInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
