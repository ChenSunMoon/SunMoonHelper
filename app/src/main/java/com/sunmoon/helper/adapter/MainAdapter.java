package com.sunmoon.helper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by SunMoon on 2016/11/3.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public MainAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

}
