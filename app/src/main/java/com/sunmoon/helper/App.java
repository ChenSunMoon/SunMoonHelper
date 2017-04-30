package com.sunmoon.helper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.sunmoon.helper.callBack.WeexImageAdapter;
import com.sunmoon.helper.model.DaoMaster;
import com.sunmoon.helper.model.DaoSession;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;

import sunmoon.voice.recognition.SpeechOcr;

/**
 * Created by SunMoon on 2016/10/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化语音识别
        SpeechOcr.getInstance().init(this);
        Logger.init("SunMoon");

        // 初始化GreenDao
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(this,"SunMoon",null);
        SQLiteDatabase db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        daoSession=daoMaster.newSession();

        // 初始化Weex
        InitConfig config=new InitConfig.Builder().setImgAdapter(new WeexImageAdapter()).build();
        WXSDKEngine.initialize(this,config);

    }
    private static DaoSession daoSession;
    public static DaoSession getDaosession(){
           return daoSession;
    }

}
