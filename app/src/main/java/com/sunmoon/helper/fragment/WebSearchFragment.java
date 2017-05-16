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
import com.sunmoon.helper.presenter.WebPresenter;

/**
 * Created by SunMoon on 2017/5/4.
 */

public class WebSearchFragment extends BaseFragment<FragmentBrowserBinding, WebPresenter>{
    private String baseUrl = "https://www.baidu.com/s?wd=";
    private String  url;
    private String keyword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyword = getArguments().getString("keyword");
        url = baseUrl + keyword;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_browser,container,false);
        initWebSetting();
        loadUrl(url);
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
        fragment.setArguments(args);
        return fragment;
    }
}
