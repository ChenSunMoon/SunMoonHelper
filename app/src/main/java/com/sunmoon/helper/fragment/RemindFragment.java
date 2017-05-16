package com.sunmoon.helper.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.FragmentRemindBinding;
import com.sunmoon.helper.presenter.RemindPresenter;
import com.sunmoon.helper.view.RemindView;

import org.joda.time.DateTime;

/**
 * Created by SunMoon on 2017/5/15.
 */

public class RemindFragment extends BaseFragment<FragmentRemindBinding,RemindPresenter> implements RemindView {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = new RemindPresenter();
        p.setView(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_remind,container,false);
        return b.getRoot();
    }
    public static RemindFragment newInstance(int year,
                                   int monthOfYear,
                                   int dayOfMonth,
                                   int hourOfDay,
                                   int minuteOfHour,
                                   String remindContent){
        RemindFragment fragment = new RemindFragment();
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("monthOfYear", monthOfYear);
        args.putInt("dayOfMonth", dayOfMonth);
        args.putInt("hourOfDay", hourOfDay);
        args.putInt("minuteOfHour", minuteOfHour);
        args.putString("remindContent",remindContent);
        fragment.setArguments(args);
        return  fragment;

    }
}
