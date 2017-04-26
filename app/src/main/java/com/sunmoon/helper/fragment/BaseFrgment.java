package com.sunmoon.helper.fragment;

import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Created by SunMoon on 2016/11/3.
 */

public class BaseFrgment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showToast(String content){
        Toast.makeText(getContext(),content,Toast.LENGTH_SHORT).show();
    }
}
