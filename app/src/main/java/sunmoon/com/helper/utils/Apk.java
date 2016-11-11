package sunmoon.com.helper.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by SunMoon on 2016/11/2.
 */

public class Apk {
    /**
     * 打开app
     * */
    public static void openApp(Activity activity,String packageName){
        Intent intentA = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        activity.startActivity(intentA);
    }
    /**
     * 卸载app
     * */
    public static void unInstallApp(Activity activity,String packageName){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" +packageName));
        activity.startActivity(intent);
    }
}
