package com.sunmoon.helper.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by SunMoon on 2016/11/6.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private CompositeSubscription compositeSubscription;
    /**
    * @param subscription 添加的订阅
    * */
    public void addSubscribe(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }
     /**
     *  取消订阅
     * */
    public void unSubscribe() {
        if (compositeSubscription != null) compositeSubscription.unsubscribe();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
}
