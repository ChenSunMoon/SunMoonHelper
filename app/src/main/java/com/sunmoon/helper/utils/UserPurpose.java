package com.sunmoon.helper.utils;

import com.sunmoon.helper.model.AnalyzeCommand;
import com.sunmoon.helper.model.UserCommand;

import java.util.ArrayList;
import java.util.List;


/*
 *根据字符串分析用户意图
 */
public class UserPurpose {
    private List<AnalyzeCommand> list;
    public UserPurpose() {
        this.list=new ArrayList<AnalyzeCommand>();
        initData();
    }

    private void initData() {
        this.list.add(new AnalyzeCommand(UserCommand.COMMAND_SEARCH, new String[]{"搜索", "搜一下", "搜", "百度"}));
        this.list.add(new AnalyzeCommand(UserCommand.COMMAND_CALLPHONE, new String[]{"打电话给", "呼叫", "打电话", "打给"}));
        this.list.add(new AnalyzeCommand(UserCommand.COMMAND_OPEN_APP, new String[]{"打开", "启动"}));
        this.list.add(new AnalyzeCommand(UserCommand.COMMAND_DELETE_APP, new String[]{"卸载", "删除"}));
    }

    public UserCommand getUserPurpose(String content) {
        for (int i=0;i<list.size();i++)
        {
            AnalyzeCommand item=list.get(i);
            for (int j=0;j<item.getModels().length;j++) {
                if (content.contains(item.getModels()[j])) {
                    String cmdCon = content.replace(item.getModels()[j], "");
                    return new UserCommand(item.getCommand(), cmdCon);
                }
            }
        }
        return UserCommand.getDefaultCommand(content);
    }

}
