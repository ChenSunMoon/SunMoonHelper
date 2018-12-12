package com.sunmoon.helper.modules.main

import android.Manifest
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.sunmoon.helper.R
import com.sunmoon.helper.base.BaseActivity
import com.sunmoon.helper.databinding.ActivityMainBinding
import com.sunmoon.helper.modules.helper.HelperFragment
import com.sunmoon.helper.modules.helper.SettingActivity
import com.sunmoon.helper.utils.IntentUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : BaseActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val b = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        setSupportActionBar(b.toolbar)
        val toggle = ActionBarDrawerToggle(this, b.drawerLayout, b.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        b.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        b.navView.setNavigationItemSelectedListener { item ->
            val id = item.itemId
            if (id == R.id.nav_voice) {
                changeFragment(HelperFragment::class.java.name, "语音助手")
            }
            b.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
       

        addFragments()

        checkPermissions()
    }



    private fun checkPermissions() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { aBoolean ->
                    if (!aBoolean) {
                        showToast("需要获取录音权限")
                        val intent = IntentUtils.getAppDetailSettingIntent(baseContext)
                        startActivity(intent)
                    } else {

                    }
                }
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            val i = Intent(applicationContext,SettingActivity::class.java)
            startActivity(i)
            true
        } else super.onOptionsItemSelected(item)

    }
    private fun addFragments() {
        supportFragmentManager.beginTransaction().add(R.id.fl_content, HelperFragment.newInstance(), HelperFragment::class.java.name)
                .commitAllowingStateLoss()
    }
    fun changeFragment(tagName:String, name: String) {
        val fragment = supportFragmentManager.findFragmentByTag(tagName)
        if (fragment!=null) {
            supportFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss()
        } else {
            showToast("未找到页面：$tagName" )
        }
        title = name
    }

}
