package com.sunmoon.helper.modules.helper

import android.app.ActionBar
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.PopupWindow
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.sunmoon.helper.R
import com.sunmoon.helper.base.BaseFragment
import com.sunmoon.helper.databinding.FragmentHelperBinding
import com.sunmoon.helper.databinding.HelperFastActionBinding
import com.sunmoon.helper.modules.remind.EditRemindActivity
import sunmoon.voice.control.SpeechRec
import sunmoon.voice.control.VoiceWakeup
import sunmoon.voice.rec.IRecogListener
import sunmoon.voice.rec.RecogResult
import sunmoon.voice.wakeup.IWakeupListener
import sunmoon.voice.wakeup.WakeUpResult


/**
 * Created by SunMoon on 2017/4/9.
 */

class HelperFragment : BaseFragment<FragmentHelperBinding>(), IRecogListener {
    override fun onAsrReady() {
        viewModel?.tip?.set("可以说话了")
        b.btStart.isClickable = false
    }

    override fun onAsrBegin() {
        
    }

    override fun onAsrEnd() {
        
    }

    override fun onAsrPartialResult(results: Array<out String>?, recogResult: RecogResult?) {
        if(results != null &&results.isNotEmpty()) {
            viewModel?.tip?.set(results[0])
        }
    }

    override fun onAsrFinalResult(results: Array<out String>?, recogResult: RecogResult?) {
        if (results != null && results.isNotEmpty()) {
            viewModel!!.execCommand(results[0])
        }
        viewModel?.tip?.set("点击开始或试试“小明帮我”")
    }

    override fun onAsrFinish(res: RecogResult?) {
        b.btStart.isClickable = true
    }

    override fun onAsrFinishError(errorCode: Int, subErrorCode: Int, errorMessage: String?, descMessage: String?, recogResult: RecogResult?) {
        
    }

    override fun onAsrLongFinish() {
        
    }

    override fun onAsrVolume(volumePercent: Int, volume: Int) {
        
    }

    override fun onAsrAudio(data: ByteArray?, offset: Int, length: Int) {
        
    }

    override fun onAsrExit() {
        
    }

    override fun onAsrOnlineNluResult(nluResult: String?) {
        
    }

    override fun onOfflineLoaded() {
        
    }

    override fun onOfflineUnLoaded() {
        
    }

    override fun lazyLoad() {
        if (HelperSetting.canWakeUp) {
            VoiceWakeup.getInstance().start()
        }
    }


    var viewModel:HelperViewModel ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HelperSetting.getSetting(context!!)
        viewModel = ViewModelProviders.of(activity!!).get(HelperViewModel::class.java)
        viewModel?.init(activity!!)
        SpeechRec.getInstance().onEvent(this)
        VoiceWakeup.getInstance().addListener(object :IWakeupListener{
            override fun onSuccess(word: String?, result: WakeUpResult?) {
                SpeechRec.getInstance().start()
            }

            override fun onStop() {
            }

            override fun onError(errorCode: Int, errorMessge: String?, result: WakeUpResult?) {
            }

            override fun onASrAudio(data: ByteArray?, offset: Int, length: Int) {
            }
        })

        lifecycle.addObserver(viewModel!!)
    }

    override fun onPause() {
        super.onPause()
        VoiceWakeup.getInstance().stop()
    }
    override fun onDestroy() {
        super.onDestroy()
        SpeechRec.getInstance().unregister()
    }
    override fun setLayoutRes(): Int {
        return R.layout.fragment_helper
    }
    override fun initView() {
        b?.viewModel = viewModel
//        b?.btStart?.setOnClickListener {
//
//            ToastUtils.showShort("start")
//        }
        b?.icKeyboard?.setOnClickListener {
            var isSpeech = viewModel!!.isSpeechModel.get() == false
            viewModel?.isSpeechModel?.set(isSpeech)
            var inputManager =b.edStart.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (!isSpeech) {
                b.edStart.visibility = View.VISIBLE
                b.edStart.isFocusable = true
                b.edStart.isFocusableInTouchMode = true
                b.edStart.requestFocus()
                inputManager.showSoftInput(b.edStart, 0)
            } else {
                b.edStart.visibility = View.GONE
                inputManager.hideSoftInputFromWindow(b.edStart.windowToken,0)
            }
        }
        b.edStart.setOnEditorActionListener { v, actionId, event ->
            if (actionId ==EditorInfo.IME_ACTION_DONE) {
                var text = b.edStart.text.toString().trim()
                if (text.isNotEmpty()) {
                    viewModel?.execCommand(text)
                }
            }
            b.edStart.text = null
            true
        }

        b.icAdd.setOnClickListener {
            showFastAction()
        }
    }
    private fun showFastAction() {
       // var view = LayoutInflater.from(activity).inflate(R.layout.helper_fast_action,null,false)
        var view = DataBindingUtil.inflate<HelperFastActionBinding>(layoutInflater, R.layout.helper_fast_action, null,false)
        view.llAddRemind.setOnClickListener {
            var intent = Intent(context,EditRemindActivity::class.java)
            intent.putExtra("isNew", true)
            ActivityUtils.startActivity(intent)
        }
        var window = PopupWindow(view.root,ConvertUtils.dp2px(120f), ViewGroup.LayoutParams.WRAP_CONTENT,true)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.animationStyle = R.style.Animation_AppCompat_DropDownUp

        window.isTouchable = true
        window.showAsDropDown(b.icAdd, 0 ,ConvertUtils.dp2px(-90f))
    }
    companion object {
        fun newInstance(): HelperFragment {
            return HelperFragment()
        }
    }
}
