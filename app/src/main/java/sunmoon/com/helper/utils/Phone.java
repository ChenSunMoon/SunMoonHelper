package sunmoon.com.helper.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by SunMoon on 2016/11/1.
 */

public class Phone {
    public static void callPerson(Activity activity, String name) {
        Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + name));
        activity.startActivity(intentCall);
    }

    public static void fastCallPerson(Activity activity, String name) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + name));
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        activity.startActivity(intent);
    }
}
