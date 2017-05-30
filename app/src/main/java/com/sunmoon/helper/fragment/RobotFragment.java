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
import com.sunmoon.helper.adapter.VoiceAdapter;
import com.sunmoon.helper.databinding.ActivityVoiceBinding;
import com.sunmoon.helper.model.Message;
import com.sunmoon.helper.presenter.RobotPresenter;
import com.sunmoon.helper.view.ChatView;

/**
 * Created by SunMoon on 2017/4/9.
 */

public class RobotFragment extends BaseFragment<ActivityVoiceBinding,RobotPresenter> implements ChatView{
    private VoiceAdapter adapter;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        p = new RobotPresenter(getActivity());
        p.setView(this);
        adapter=new VoiceAdapter(getContext());
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
        b.rvContent.setAdapter(adapter);
        b.setPresent(p);
        return b.getRoot();
    }
    public static Fragment newInstance(){
        return new RobotFragment();
    }

    @Override
    public void sendMsg(Message message) {
        if (fragment != null){
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
            fragment = null;

        }
        adapter.addMessage(message);
        smoothBottom();
    }

    @Override
    public void receiveMsg(Message message) {
        adapter.addMessage(message);
        smoothBottom();
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void openFragment(Fragment fragment) {
        changeFragment(fragment);
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
    private void smoothBottom(){
        b.rvContent.smoothScrollToPosition(adapter.getItemCount()-1);
    }
    private Fragment fragment;

    public void changeFragment(Fragment fragment){
        this.fragment =fragment;
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fl_robot_content, this.fragment);
        ft.commit();
    }
}
