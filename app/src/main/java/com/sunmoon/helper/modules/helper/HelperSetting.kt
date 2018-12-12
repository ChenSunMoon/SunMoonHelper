package com.sunmoon.helper.modules.helper

import android.content.Context
import android.support.v7.preference.PreferenceManager

/**
 * 作者：Sunmoon
 * 时间：2017/11/21
 */
object HelperSetting {
    var canSpeak = true // 是否开启语音合成
    var canWakeUp = false // 是否开启语音唤醒
    object Key {
        val SETTING_TTS = "setting_tts"
        val WAKE_UP = "setting_wake_up"
    }
    fun getSetting(context: Context){
        var setting = PreferenceManager.getDefaultSharedPreferences(context)
        canSpeak = setting.getBoolean(HelperSetting.Key.SETTING_TTS,true)
        canWakeUp = setting.getBoolean(HelperSetting.Key.WAKE_UP,true)
    }
}