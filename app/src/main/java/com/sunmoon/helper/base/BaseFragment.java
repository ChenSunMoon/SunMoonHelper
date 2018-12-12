package com.sunmoon.helper.base;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by SunMoon on 2016/11/3.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    protected T b;
    protected boolean mPrepared;
    protected boolean mVisibleToUser;
    protected boolean isInvokedSetUserVisibleHint = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, setLayoutRes(), container,false);
        mPrepared = true;
        return b.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInvokedSetUserVisibleHint){
            mVisibleToUser = true;
            tryLoad();
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mVisibleToUser = isVisibleToUser;
        isInvokedSetUserVisibleHint=true;
        tryLoad();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        tryLoad();
    }

    private void tryLoad() {
        if (mVisibleToUser && mPrepared) {
            lazyLoad();
        }
    }
    public abstract void lazyLoad();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public  abstract @LayoutRes int setLayoutRes();
   public abstract void initView();
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showToast(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPrepared = false;
    }
}
