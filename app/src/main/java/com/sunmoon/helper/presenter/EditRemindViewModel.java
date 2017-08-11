package com.sunmoon.helper.presenter;

import android.content.Context;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.ViewGroup;


import com.sunmoon.helper.App;
import com.sunmoon.helper.model.Remind;
import com.sunmoon.helper.model.RemindDao;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

/**
 * Created by SunMoon on 2017/5/16.
 */

public class EditRemindViewModel extends Presenter {
    private ObservableField<Integer> hour;
    private ObservableField<Integer> minute;
    private long remindId;
    private RemindDao dao = App.getDaoSession().getRemindDao();
    private Remind remind;
    private Context context;
    private String remindContent;
    public EditRemindViewModel(Context context){
        this.context = context;
    }
    /**
     * @param id 提醒id
     * 编辑已存在的提醒
     * */
    public void initById(long id) {
        this.remindId = id;
        remind = dao.load(id);
        this.remindContent = remind.getContent();
        DateTime dt = new DateTime(remind.getTime());
        this.hour.set(dt.getHourOfDay());
        this.minute.set(dt.getMinuteOfHour());
    }
/**
 * @param similarTime 与时间相似的文字，示例: 22点30, 十二点30
 * @param remindContent 提醒内容
 *  添加新的提醒
**/
    public void initNew(String similarTime, String remindContent) {
        remind  = new Remind();
        remind.setContent(remindContent);
        this.remindContent = remindContent;
        DateTime dt = getDateTime(similarTime);
        this.hour.set(dt.getHourOfDay());
        this.minute.set(dt.getMinuteOfHour());
    }
    public void confirmRemind() {
        if (remindContent.equals("") || remindContent == null){
            com.sunmoon.helper.utils.Toast.show(context, "提醒内容不能为空！");
            return;
        }
        remind.setContent(remindContent);
        DateTime dateTime = new DateTime().withField(DateTimeFieldType.clockhourOfDay(),hour.get()).withField(DateTimeFieldType.minuteOfHour(),minute.get());
        remind.setTime(dateTime.toString());
        if (remindId == 0) {
            remind.setId(System.currentTimeMillis());
            dao.insert(remind);
        } else {
            dao.update(remind);
        }
    }


    @Override
    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
    }


public DateTime getDateTime(String similarTime){
    try {
        String[] timePoint = new String[]{"点", ":", "："};
        String hStr = "";
        String mStr = "";
        for (String s : timePoint) {
            if (similarTime.contains(s)) {
                String[] temp = similarTime.split(s);
                hStr = temp[0];
                if (temp.length == 1) {
                    mStr = "00";
                } else {
                    mStr = temp[1];
                }

                break;
            }
        }
        int h = hanZiToInt(hStr);
        int m = hanZiToInt(mStr);
        if (h > 23 || h < 0) {
            h = 12;
        }
        if (m > 60 || m < 0) {
            m = 30;
        }

        return new DateTime().withField(DateTimeFieldType.clockhourOfDay(),h).withField(DateTimeFieldType.minuteOfHour(),m);
    } catch (Exception e){
        e.printStackTrace();
        return  new DateTime();

    }
}

    /**
     * 汉字字符串转数字转数字字符串
     * 范伟00—60
     */
    public int hanZiToInt(String hanZi) {
        char[] hanZis = hanZi.toCharArray();
        int[] res = new int[]{0, 0};
        int length = hanZis.length >= 3 ? 3 : hanZis.length;
        for (int i = hanZis.length - 1; i >= hanZis.length - length; i--) {
            int raw = getNumberByChar(hanZis[i]);
            if (raw == 10) {
                res[1] = 10;
                continue;
            }
            if (i == hanZis.length - 1) { // 个位
                res[0] = raw;
            } else {
                res[1] = raw * 10;
            }
        }
        return res[0] + res[1];
    }

    /**
     * 汉字字符串转数字转数字字符串
     * 范伟0—10
     */
    public int getNumberByChar(char hanZi) {
        char[] numbers = new char[]{'零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十'};
        for (int i = 0; i < numbers.length; i++) {
            if (hanZi == numbers[i] || hanZi == (48 + i)) {
                return i;
            }
        }
        return 0;
    }

    public String getRemindContent() {
        return remindContent;
    }

    public int getHour() {
        return hour.get();
    }

    public void setHour(int hour) {
        this.hour.set(hour);
    }

    public int getMinute() {
        return minute.get();
    }

    public void setMinute(int minute) {
        this.minute.set(minute);
    }

    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }
}
