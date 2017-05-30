package com.sunmoon.helper.activity;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.ActivityFragmentBinding;

/**
 * Created by SunMoon on 2017/5/28.
 */

public class FragmentActivity extends BaseActivity {
    private ActivityFragmentBinding b;
    public static  Fragment fragment;
    public static String title;
    private String TAG = "FragmentActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b  = DataBindingUtil.setContentView(this, R.layout.activity_fragment);
        setSupportActionBar(b.toolbar);
        if (fragment == null){
            Log.e(TAG, "please set fragment and title");
        }
        setImmersive(true);
        getFragmentManager().beginTransaction().add(R.id.fl_fragment_content,fragment).commit();
        setTitle(title);
    }
    public static void setFragment(Fragment fragment,String title){
        FragmentActivity.fragment = fragment;
        FragmentActivity.title = title;
    }
    public  void removeFragment(){
        getFragmentManager().beginTransaction().remove(fragment);
        FragmentActivity.fragment = null;
        FragmentActivity.title = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        removeFragment();
        super.onDestroy();
    }
}
