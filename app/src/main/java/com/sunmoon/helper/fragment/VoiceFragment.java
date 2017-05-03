package com.sunmoon.helper.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
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
import com.sunmoon.helper.presenter.VoicePresenter;
import com.sunmoon.helper.view.ChatView;

/**
 * Created by SunMoon on 2017/4/9.
 */

public class VoiceFragment extends BaseFrgment implements ChatView{
    private ActivityVoiceBinding b;
    private VoiceAdapter adapter;
    private VoicePresenter presenter ;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new VoicePresenter(getContext());
        presenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.startWakeUp();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stopAll();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater,R.layout.activity_voice,container,false);

        adapter = new VoiceAdapter(getContext());

        LinearLayoutManager lineManager=new LinearLayoutManager(getContext());
        b.rvContent.setLayoutManager(lineManager);
        b.rvContent.setAdapter(adapter);

        b.setVoicePresenter(presenter);
        return b.getRoot();
    }
    public static Fragment newInstance(){
        return new VoiceFragment();
    }

    @Override
    public void sendMsg(Message message) {
        adapter.addMessage(message);
    }

    @Override
    public void receiveMsg(Message message) {
        adapter.addMessage(message);
    }

    @Override
    public void onRmsChanged(float v) {
        b.vVoice.setVolume(v/50);
    }

    /**
     * 滑动到底部
     * */
    private void smoothBottom(){
        b.rvContent.smoothScrollToPosition(adapter.getItemCount()-1);
    }
}
