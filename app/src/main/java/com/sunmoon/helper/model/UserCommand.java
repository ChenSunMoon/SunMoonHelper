package com.sunmoon.helper.model;

/**
 * Created by chenm on 2015/12/5.
 */
public class UserCommand {
    private int command;
    private String content ="";
    //命令词
    public static final int COMMAND_TULING=1;//聊天
    public static final int COMMAND_SEARCH=2;//搜索
    public static final int COMMAND_CALLPHONE=3;//打电话
    public static final int COMMAND_OPEN_APP=4;//打开APP
    public static final int COMMAND_DELETE_APP=5;//卸载APP
    //分析模型

    public  static  final String[] APP_COMMADN_DELETE={};
    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public UserCommand(int command) {
        this.command = command;
    }

    public UserCommand(int command, String content) {
        this.command = command;
        setContent(content);
    }

    public static UserCommand getDefaultCommand(String content){
        return new UserCommand(COMMAND_TULING,content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.replaceAll(" ", "").replace("\n", "").replace(",", "").replace("，", "").replace("。", "").toUpperCase();;
    }
}
