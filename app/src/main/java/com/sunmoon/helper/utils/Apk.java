package com.sunmoon.helper.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.sunmoon.helper.common.Flag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunMoon on 2016/11/2.
 */

public class Apk {
    /**
     * 打开app
     * */
    public static void openApp(Context context, String packageName){
        Intent intentA = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intentA);
    }

    /**
     * 卸载app
     * */
    public static void unInstallApp(Context context,String packageName){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" +packageName));
        context.startActivity(intent);
    }
    public static  String getAppPackageName(Context context,String appName)
    {
        PackageManager packageManager;
        packageManager=context.getPackageManager();
        List<PackageInfo> apkList=packageManager.getInstalledPackages(0);
        PackageInfo packageInfo=null;
        for(int i=0;i<apkList.size();i++){
            if (HanyuPinyin.equalsLinePinYing(packageManager.getApplicationLabel(apkList.get(i).applicationInfo).toString(),appName))
            {
                packageInfo =apkList.get(i);
            }
        }
        return packageInfo!=null ?packageInfo.packageName: Flag.FAIL;
    }
    public static  String getAppPackageName(Context context,List<PackageInfo> packageInfos,String appName)
    {
        PackageManager packageManager;
        packageManager=context.getPackageManager();
        PackageInfo packageInfo=null;
        for(int i=0;i<packageInfos.size();i++){
            if (HanyuPinyin.equalsLinePinYing(packageManager.getApplicationLabel(packageInfos.get(i).applicationInfo).toString(),appName))
            {
                packageInfo =packageInfos.get(i);
            }
        }
        return packageInfo!=null ?packageInfo.packageName: Flag.FAIL;
    }
    public static List<PackageInfo> getPackgeInfos(Context context){
        PackageManager packageManager;
        packageManager=context.getPackageManager();
        List<PackageInfo> packageInfos=packageManager.getInstalledPackages(0);
        return packageInfos;
    }
}
