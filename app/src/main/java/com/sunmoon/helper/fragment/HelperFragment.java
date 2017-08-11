package com.sunmoon.helper.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.ActivityVoiceBinding;
import com.sunmoon.helper.model.Message;
import com.sunmoon.helper.presenter.HelperPresenter;
import com.sunmoon.helper.view.ChatView;

/**
 * Created by SunMoon on 2017/4/9.
 */

public class HelperFragment extends BaseFragment<ActivityVoiceBinding,HelperPresenter> implements ChatView{
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        p = new HelperPresenter(getActivity());
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater,R.layout.activity_voice,container,false);
        LinearLayoutManager lineManager=new LinearLayoutManager(getContext());
        b.rvContent.setLayoutManager(lineManager);
        b.rvContent.setAdapter(p.getAdapter());
        b.setPresent(p);
        return b.getRoot();
    }
    public static Fragment newInstance(){
        return new HelperFragment();
    }

    @Override
    public void onRmsChanged(float v) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void startRecDialog(Intent intent,int rec_code) {
        startActivityForResult(intent,rec_code);
    }

    /**
     * 滑动到底部
     * */
    @Override
    public void smoothBottom(){
        b.rvContent.smoothScrollToPosition(b.rvContent.getAdapter().getItemCount()-1);
    }
    private Fragment fragment;

}
