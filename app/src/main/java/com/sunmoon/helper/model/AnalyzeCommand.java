package com.sunmoon.helper.model;

/**
 * Created by SunMoon on 2016/11/18.
 */

public class AnalyzeCommand {
    private int command;
    private String[] models;

    public AnalyzeCommand(int command, String[] models) {
        this.command = command;
        this.models = models;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public String[] getModels() {
        return models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }
}
