package com.sunmoon.helper.modules.remind

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.sunmoon.helper.R
import com.sunmoon.helper.base.BaseActivity
import com.sunmoon.helper.databinding.ActivityEditRemindBinding

/**
 * Created by SunMoon on 2017/8/2.
 */
class EditRemindActivity : BaseActivity() {
    lateinit var b: ActivityEditRemindBinding
    lateinit var vm: EditRemindViewModel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = EditRemindViewModel()
        val isNew = intent.getBooleanExtra("isNew", true)
//        if (isNew) {
//            val remindContent = intent.getStringExtra("remindContent")
//            val similarTime = intent.getStringExtra("similarTime")
//            vm.initNew(similarTime, remindContent)
//        } else {
//            val id = intent.getLongExtra("remindId", 0)
//            vm.initById(id)
//        }
        b = DataBindingUtil.setContentView(this, R.layout.activity_edit_remind)
        b.vm = vm
        b.tp.hour
    }
}