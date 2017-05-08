package com.sunmoon.helper.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.FragmentBrowserBinding;

/**
 * Created by SunMoon on 2017/5/4.
 */

public class WebSearchFragment extends BaseFragment{
    FragmentBrowserBinding b;
    private String keyword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyword = getArguments().getString("keyword");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_browser,container,false);
        initWebSetting();
        return b.getRoot();
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
    public void loadUrl(String url){
        b.wb.loadUrl(url);
    }
    public static WebSearchFragment newInstance(String keyword){
        WebSearchFragment fragment = new WebSearchFragment();
        Bundle args = new Bundle();
        args.putString("keyword", keyword);
        return fragment;
    }
}
