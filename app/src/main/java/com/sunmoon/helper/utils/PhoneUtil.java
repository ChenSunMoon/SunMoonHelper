package com.sunmoon.helper.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;

import com.sunmoon.helper.model.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunMoon on 2016/11/1.
 */

public class PhoneUtil {
    public List<Phone> phones;
    /**
     * 拨打某人电话
     **/
    public static void callPerson(Context context, String name) {
        Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + name));
        context.startActivity(intentCall);
    }
    /**
     * 直接拨打电话
     **/
    public static boolean fastCallPerson(Context context, String name) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + name));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        context.startActivity(intent);
        return true;
    }
    /**
     * 获取联系人列表
    **/
    public static List<Phone> getPhones(Context context){
        List<Phone> phoneList=new ArrayList<>();
        try {
            Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            String phoneNumber;
            String phoneName;
            assert cursor != null;
            while (cursor.moveToNext()) {
                phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //长度大于5不添加
                if(phoneName.length()<5) {
                    Phone phoneInfo = new Phone(phoneName, phoneNumber.replaceAll(" ",""));
                    phoneList.add(phoneInfo);
                }
            }
            cursor.close();
            return  phoneList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return phoneList;
    }
    /**
     * 查找联系人
     **/
    public static List<Phone> getPhonesByName(List<Phone> phoneInfos, String name){
        List<Phone> result=new ArrayList<>();
        for (int i = 0; i < phoneInfos.size(); i++) {
            if (HanyuPinyin.equalsLinePinYing(name,phoneInfos.get(i).getName()))
            {
               result.add(phoneInfos.get(i));
            }
        }
        if (result.size()<1) // 未在通讯录中找到联系人
        {
            if(name.matches("[0-9]+"))
            {
                result.add(new Phone(name,name));
            }
        }
        return result;
    }
}
