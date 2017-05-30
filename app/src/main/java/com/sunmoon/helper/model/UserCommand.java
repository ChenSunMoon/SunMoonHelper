package com.sunmoon.helper.model;

/**
 * Created by chenm on 2015/12/5.
 */
public class UserCommand {

    // 命令类型的枚举值
    public static final int COMMAND_CHAT = 1;// 聊天
    public static final int COMMAND_SEARCH = 2;// 搜索
    public static final int COMMAND_CALL_PHONE = 3;// 打电话
    public static final int COMMAND_OPEN_APP = 4;// 打开APP
    public static final int COMMAND_DELETE_APP = 5 ;// 卸载APP
    public static final int COMMAND_REMIND = 6;// 卸载APP
    private Sentence sentence;
    private int type; //命令类型

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
