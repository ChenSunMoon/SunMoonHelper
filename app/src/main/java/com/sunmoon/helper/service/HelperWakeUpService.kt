package com.sunmoon.helper.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.sunmoon.helper.modules.main.MainActivity
import com.sunmoon.helper.utils.SystemHelper
import sunmoon.voice.control.VoiceWakeup
import sunmoon.voice.wakeup.IWakeupListener
import sunmoon.voice.wakeup.WakeUpResult

class HelperWakeUpService: Service(),IWakeupListener {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        ToastUtils.showShort("创建成功")
        VoiceWakeup.getInstance().addListener(this)
    }

    override fun onDestroy() {
        VoiceWakeup.getInstance().unregisterListener()
        super.onDestroy()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onSuccess(word: String?, result: WakeUpResult?) {
        if(!AppUtils.isAppForeground()){
            ToastUtils.showShort("启动" )
            startLocalApp()
        }
    }

    override fun onASrAudio(data: ByteArray?, offset: Int, length: Int) {

    }

    override fun onError(errorCode: Int, errorMessge: String?, result: WakeUpResult?) {

    }

    override fun onStop() {

    }
    fun  startLocalApp() {
        val packageManager = getPackageManager()
        val intent = packageManager.getLaunchIntentForPackage("com.sunmoon.helper")
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        /**android.intent.action.MAIN：打开另一程序
         */
        intent.setAction("android.intent.action.MAIN");
        /**
         * FLAG_ACTIVITY_SINGLE_TOP:
         * 如果当前栈顶的activity就是要启动的activity,则不会再启动一个新的activity
         */
        intent.putExtra("isWakeUp", true)
//        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }
}