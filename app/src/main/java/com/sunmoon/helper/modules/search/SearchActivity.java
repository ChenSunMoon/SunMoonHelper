package com.sunmoon.helper.modules.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sunmoon.helper.R;
import com.sunmoon.helper.base.BaseActivity;
import com.sunmoon.helper.databinding.ActivitySearchBinding;
import com.sunmoon.helper.modules.browser.BrowserActivity;
import com.sunmoon.helper.modules.search.SearchViewModel;

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
        b = DataBindingUtil.setContentView(this,R.layout.activity_search);
        setSupportActionBar(b.toolBar);
        b.toolBar.setTitle(vm.getKeyword());
        b.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initWebSetting();
        b.wb.setWebChromeClient(new WebChromeClient(){
        });
        b.wb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent =new Intent(SearchActivity.this, BrowserActivity.class);
                intent.putExtra("url",url );
                startActivity(intent);
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
}
