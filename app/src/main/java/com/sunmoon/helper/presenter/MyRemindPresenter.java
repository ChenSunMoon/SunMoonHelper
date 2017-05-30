package com.sunmoon.helper.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunmoon.helper.App;
import com.sunmoon.helper.model.Remind;
import com.sunmoon.helper.model.RemindDao;
import com.sunmoon.helper.view.MyRemindView;

import java.util.List;

/**
 * Created by SunMoon on 2017/5/23.
 */

public class MyRemindPresenter extends Presenter<MyRemindView> {
    private RemindDao dao;
    public List<Remind> list;
    public MyRemindPresenter () {
        dao = App.getDaoSession().getRemindDao();

    }

    @Override
    public void onResume() {
        super.onResume();
        list = dao.loadAll();
        v.iniReminds(list);
    }

    public void deleteRemind(Remind item, int i) {
        dao.delete(item);
        v.deleteRemind(i);
    }

    public void updateRemind(Remind item,int i) {
        dao.update(item);
    }
}
