package com.sunmoon.helper.modules.helper

import android.annotation.TargetApi
import android.app.Fragment
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sunmoon.helper.R
import com.sunmoon.helper.databinding.FragmentHelperBinding
import com.sunmoon.helper.base.BaseFragment

/**
 * Created by SunMoon on 2017/4/9.
 */

class HelperFragment : BaseFragment(), ChatView {
    lateinit var b: FragmentHelperBinding
    lateinit var vm: HelperViewModel

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        vm = HelperViewModel(activity)
        vm.setView(this)

        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
         vm.startVoiceWakeUp()
    }

    override fun onStop() {
        super.onStop()
        vm.stopVoiceWakeUp()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        b = DataBindingUtil.inflate<FragmentHelperBinding>(inflater, R.layout.fragment_helper, container, false)
        b.present = vm
        b.msgList.setAdapter(vm.adapter)
        return b.root
    }

    override fun onRmsChanged(v: Float) {}
    var REQ_CODE = 1
    override fun onGetDialogIntent(intent: Intent) {
        startActivityForResult(intent, REQ_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE && data != null) {
            vm.onResults(data.extras)
        }
    }
    /**
     * 滑动到底部
     */
    override fun smoothBottom() {

    }

    companion object {
        fun newInstance(): Fragment {
            return HelperFragment()
        }
    }

}
