package com.sunmoon.helper.modules.helper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.sunmoon.helper.R

/**
 * 作者：Sunmoon
 * 时间：2017/11/20
 */
class SettingActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helper_setting)
        val toolBar =findViewById(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolBar)
        toolBar.setNavigationOnClickListener { this.finish() }
        supportFragmentManager.beginTransaction().add(R.id.fl_content, SettingFragment()).commitAllowingStateLoss()
    }
}