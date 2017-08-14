package com.sunmoon.helper.modules.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;

import com.sunmoon.helper.R;
import com.sunmoon.helper.base.BaseActivity;
import com.sunmoon.helper.databinding.ActivitySearchBinding;

/**
 * Created by SunMoon on 2017/5/4.
 */

public class SearchActivity extends BaseActivity{
    private ActivitySearchBinding b;
    private SearchViewModel vm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = new SearchViewModel(getIntent().getStringExtra("keyword"));
        b= DataBindingUtil.setContentView(this,R.layout.activity_search);
        initWebSetting();
        b.setVm(vm);
    }
    private void initWebSetting() {
        WebSettings  settings = b.wb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        settings.setAllowFileAccess(true);
    }
}
