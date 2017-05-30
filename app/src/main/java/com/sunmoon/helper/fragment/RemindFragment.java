package com.sunmoon.helper.fragment;

import android.app.Fragment;
import android.app.Instrumentation;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.FragmentRemindBinding;
import com.sunmoon.helper.presenter.RemindPresenter;
import com.sunmoon.helper.view.RemindView;
/**
 * Created by SunMoon on 2017/5/15.
 */

public class RemindFragment extends BaseFragment<FragmentRemindBinding,RemindPresenter> implements RemindView {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        p = new RemindPresenter(getContext());
        p.setView(this);
        long id = getArguments().getLong("remindId");
        if (id == 0 ){
            String remindContent = getArguments().getString("remindContent");
            String similarTime = getArguments().getString("similarTime");
            p.initNew(similarTime, remindContent);
        } else {
            p.initById(id);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_remind,container,false);
        p.onCreate(savedInstanceState);
        b.setPresenter(p);
        return b.getRoot();
    }
    public static RemindFragment newInstance(long remindId){
        RemindFragment fragment = new RemindFragment();
        Bundle args = new Bundle();
        args.putLong("remindId",remindId);
        args.putString("from", "activity");
        fragment.setArguments(args);
        return  fragment;

    }

    /**
     * 根据时间和提醒内容初始化fragment
     * @param qualifier 时间
     * @param remindContent 提醒内容
     * @return
     */
    public static Fragment newInstance(String qualifier, String remindContent) {
        RemindFragment fragment = new RemindFragment();
        Bundle args = new Bundle();
        args.putString("similarTime", qualifier);
        args.putString("remindContent",remindContent);
        args.putString("from", "fragment");
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setTime(int hour, int min) {
        b.tp.setHour(hour);
        b.tp.setMinute(min);
    }
    @Override
    public void  back(){
        if (getArguments().getString("from") == null || getArguments().getString("from").equals("activity") ){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                }
            }.start();
        }else {
            getParentFragment().getChildFragmentManager().beginTransaction().remove(this).commit();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getHourOfDay() {
        return b.tp.getHour();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getMinuteOfHour() {
        return b.tp.getMinute();
    }

}
