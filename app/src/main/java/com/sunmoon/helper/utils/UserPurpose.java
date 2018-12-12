package com.sunmoon.helper.utils;

import com.sunmoon.helper.model.Purpose;
import com.sunmoon.helper.model.Sentence;
import com.sunmoon.helper.model.UserCommand;

import java.util.ArrayList;
import java.util.List;


/*
 *根据字符串分析用户意图
 */
public class UserPurpose {
    private static List<Purpose> list = new ArrayList<>();

    static  {
        list.add(new Purpose(UserCommand.COMMAND_SEARCH, new String[]{"搜索", "搜一下", "搜", "百度"}));
        list.add(new Purpose(UserCommand.COMMAND_CALL_PHONE, new String[]{"打电话给", "呼叫", "打电话", "打给"}));
        list.add(new Purpose(UserCommand.COMMAND_OPEN_APP, new String[]{"打开", "启动"}));
        list.add(new Purpose(UserCommand.COMMAND_DELETE_APP, new String[]{"卸载", "删除"}));
        list.add(new Purpose(UserCommand.COMMAND_REMIND, new String[]{"提醒我","提醒"}));
    }

    public static UserCommand create(String content) {
        UserCommand userCommand = new UserCommand();
        for (int i=0;i<list.size();i++)
        {
            Purpose item = list.get(i);
            String[] commands = item.getCommands();
            for (int j=0;j<commands.length;j++) {
                if (content.contains(item.getCommands()[j])) {
                    String command = commands[j];
                    Sentence sentence = Sentence.create(content,command);
                    userCommand.setSentence(sentence);
                    userCommand.setType(item.getType());
                    return userCommand;
                }
            }
        }
        // 默认是聊天
        Sentence sentence =  new Sentence();
        sentence.setTarget(content);
        userCommand.setSentence(sentence);
        userCommand.setType(UserCommand.COMMAND_CHAT);
        return userCommand;
    }
}
