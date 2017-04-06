package com.sunmoon.helper.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.ActivityVoiceBinding;
import com.sunmoon.helper.adapter.VoiceAdapter;
import com.sunmoon.helper.presenter.VoicePresenter;

/**
 * Created by SunMoon on 2016/11/6.
 */

public class VoiceActivity extends BaseActivity{
    private ActivityVoiceBinding b;
    private VoiceAdapter voiceAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=DataBindingUtil.setContentView(this, R.layout.activity_voice);
        voiceAdapter=new VoiceAdapter(this);
        LinearLayoutManager lineManager=new LinearLayoutManager(this);
        b.rvContent.setLayoutManager(lineManager);
        b.rvContent.setAdapter(voiceAdapter);
        VoicePresenter voicePresenter =new VoicePresenter(this,voiceAdapter,b);
        b.setVoicePresenter(voicePresenter);
    }


}
