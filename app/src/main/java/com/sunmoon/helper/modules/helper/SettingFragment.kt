package com.sunmoon.helper.modules.helper

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.SwitchPreferenceCompat
import com.sunmoon.helper.R

/**
 * 作者：Sunmoon
 * 时间：2017/11/20
 */
class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.helper_setting)
        var tts = findPreference(HelperSetting.Key.SETTING_TTS) as SwitchPreferenceCompat
        tts.setOnPreferenceChangeListener { _, newValue ->
            HelperSetting.canSpeak = newValue as Boolean
            true
        }
        var wakeUp = findPreference(HelperSetting.Key.WAKE_UP) as SwitchPreferenceCompat
        wakeUp.setOnPreferenceChangeListener { _, newValue ->
            HelperSetting.canWakeUp = newValue as Boolean
            true
        }
    }
}