package com.sunmoon.helper.model;

/**
 * Created by SunMoon on 2017/5/13.
 */

public class Sentence {
    private String qualifier;
    private String command;
    private String target ="";
    /**
     * @param command 动词
     * @param content 需要拆分的语句
     */
    public static Sentence create(String content,String command){
       Sentence sentence = new Sentence();
        String[] r = content.split(command);
        sentence.setQualifier(r[0]);
        sentence.setCommand(command);
        sentence.setTarget(r[1]);
        return sentence;
    }
    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
