package com.sunmoon.helper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.Logger;
import com.sunmoon.helper.model.DaoMaster;
import com.sunmoon.helper.model.DaoSession;

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

    }
    private static DaoSession daoSession;
    public static DaoSession getDaoSession(){
           return daoSession;
    }

}
