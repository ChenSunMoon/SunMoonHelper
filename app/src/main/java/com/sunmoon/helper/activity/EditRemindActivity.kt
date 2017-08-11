package com.sunmoon.helper.activity

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.sunmoon.helper.R
import com.sunmoon.helper.databinding.ActivityEditRemindBinding
import com.sunmoon.helper.presenter.EditRemindViewModel

/**
 * Created by SunMoon on 2017/8/2.
 */
class EditRemindActivity :BaseActivity() {
    lateinit var b: ActivityEditRemindBinding
    lateinit var vm: EditRemindViewModel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = EditRemindViewModel(this)

        val id = intent.getLongExtra("remindI", 0)
        if (id == 0 as Long) {
            val remindContent = intent.getStringExtra("remindContent")
            val similarTime = intent.getStringExtra("similarTime")
            vm.initNew(similarTime, remindContent)
        } else {
            vm.initById(id)
        }
        b = DataBindingUtil.setContentView(this, R.layout.activity_edit_remind)
        b.vm = vm
        b.tp.hour
    }
}