package com.sunmoon.helper.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sunmoon.helper.presenter.Presenter;

/**
 * Created by SunMoon on 2016/11/3.
 */

public class BaseFragment<T extends ViewDataBinding,T2 extends Presenter> extends Fragment {
    public T b;
    public T2 p;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showToast(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        p.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        p.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        p.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        p.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        p.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        p.onActivityResult(requestCode, resultCode, data);
    }
}
