package com.sunmoon.helper.activity;

import android.os.Bundle;
import android.view.View;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

/**
 * Created by SunMoon on 2017/4/6.
 */

public class WeexActivity extends BaseActivity implements IWXRenderListener {
    private WXSDKInstance wxsdkInstance;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wxsdkInstance=new WXSDKInstance(this);
        wxsdkInstance.registerRenderListener(this);
        wxsdkInstance.render("WXSample", WXFileUtils.loadFileContent("hello.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        wxsdkInstance.onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wxsdkInstance.onActivityPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        wxsdkInstance.onActivityStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        wxsdkInstance.onActivityStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wxsdkInstance.onActivityDestroy();
    }
}
