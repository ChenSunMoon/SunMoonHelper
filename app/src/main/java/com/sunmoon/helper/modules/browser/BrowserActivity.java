package com.sunmoon.helper.modules.browser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sunmoon.helper.R;
import com.sunmoon.helper.base.BaseActivity;
import com.sunmoon.helper.databinding.ActivityBrowserBinding;
import com.sunmoon.helper.databinding.ActivitySearchBinding;
import com.sunmoon.helper.modules.search.SearchViewModel;

/**
 * Created by SunMoon on 2017/5/4.
 */

public class BrowserActivity extends BaseActivity{
    private ActivityBrowserBinding b;
    private BrowserViewModel vm;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = new BrowserViewModel(getIntent().getStringExtra("url"));
        b = DataBindingUtil.setContentView(this,R.layout.activity_browser);
        setSupportActionBar(b.toolBar);
        b.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initWebSetting();
        b.wb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                b.toolBar.setTitle(title);
            }
        });
        b.wb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (b.wb.canGoBack()) {
            b.wb.goBack();
        }
    }
}
