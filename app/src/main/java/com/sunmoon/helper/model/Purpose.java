package com.sunmoon.helper.model;

/**
 * Created by SunMoon on 2017/5/13.
 */

public class Purpose {
    private int type;
    private String[] commands;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getCommands() {
        return commands;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    public Purpose(int type, String[] commands) {
        this.type = type;
        this.commands = commands;
    }
}
