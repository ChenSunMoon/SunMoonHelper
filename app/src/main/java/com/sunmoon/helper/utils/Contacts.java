package com.sunmoon.helper.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.sunmoon.helper.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunMoon on 2016/11/19.
 */

public class Contacts {
    /**
     * 获取所有联系人信息
    * */
    public static List<ContactInfo> getPhoneNumberList(Context context){
        try {
            Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            List<ContactInfo> phoneList=new ArrayList<ContactInfo>();
            String phoneNumber;
            String phoneName;
            while (cursor.moveToNext()) {
                phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //长度大于5不添加
                if(phoneName.length()<5) {
                    ContactInfo contactInfo = new ContactInfo(phoneName, phoneNumber.replaceAll(" ",""));
                    phoneList.add(contactInfo);
                }
            }
            return  phoneList;
        }
        catch (Exception e){
            Toast.makeText(context, "读取通讯录出错，请检查权限", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
