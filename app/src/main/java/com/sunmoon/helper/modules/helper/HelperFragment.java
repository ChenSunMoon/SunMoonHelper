package com.sunmoon.helper.modules.helper;

import android.annotation.TargetApi;
import android.app.Fragment;
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
import com.sunmoon.helper.databinding.FragmentHelperBinding;
import com.sunmoon.helper.base.BaseFragment;

/**
 * Created by SunMoon on 2017/4/9.
 */

public class HelperFragment  extends  BaseFragment implements ChatView{
    private FragmentHelperBinding b;
    private HelperViewModel vm;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        vm = new HelperViewModel(getActivity());
        vm.setView(this);
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
        b = DataBindingUtil.inflate(inflater,R.layout.fragment_helper,container,false);
        LinearLayoutManager lineManager=new LinearLayoutManager(getContext());
        b.rvContent.setLayoutManager(lineManager);
        b.setPresent(vm);
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
        vm.onActivityResult(requestCode, resultCode, data);
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
       b.rvContent.smoothScrollToPosition(b.rvContent.getAdapter().getItemCount());
    }

}
