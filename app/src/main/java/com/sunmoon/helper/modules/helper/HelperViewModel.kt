package com.sunmoon.helper.modules.helper

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener

import com.sunmoon.helper.api.ApiManage
import com.sunmoon.helper.base.BaseViewModel
import com.sunmoon.helper.common.Flag
import com.sunmoon.helper.modules.remind.EditRemindActivity
import com.sunmoon.helper.modules.search.SearchActivity
import com.sunmoon.helper.model.Message
import com.sunmoon.helper.model.Phone
import com.sunmoon.helper.model.TuLing
import com.sunmoon.helper.model.UserCommand
import com.sunmoon.helper.utils.Apk

import sunmoon.voice.recognition.BaiduUtil

import com.sunmoon.helper.utils.PhoneUtil
import com.sunmoon.helper.utils.StringUtil
import com.sunmoon.helper.utils.UserPurpose

import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import sunmoon.voice.recognition.VoiceRec
import sunmoon.voice.recognition.VoiceWakeUp
import sunmoon.voice.recognition.WakeUpListener
import sunmoon.voice.tts.Tts


/**
 * Created by SunMoon on 2016/11/30.
 */

@TargetApi(Build.VERSION_CODES.FROYO)
class HelperViewModel(private val context: Context) : BaseViewModel(), RecognitionListener {
    private val userPurpose: UserPurpose
    val adapter: VoiceAdapter
    private var packageInfos: List<PackageInfo>? = null
    private var phoneInfos: List<Phone>? = null
    private val tts = Tts()
    private val wakeUp: VoiceWakeUp
    private val REC_CODE = 1// 语音识别
    private var v: ChatView? = null

    init {
        this.userPurpose = UserPurpose()
        adapter = VoiceAdapter(context)
        tts.init(context)

        // 语音唤醒
        wakeUp = VoiceWakeUp(context)
        wakeUp.registerListener(object : WakeUpListener {
            override fun success() {
                startVoiceRec()
            }

            override fun exit() {

            }
        })
        VoiceRec.setRecognitionListener(this)
    }

    fun setView(v: ChatView) {
        this.v = v
    }

    fun startVoiceWakeUp() {
        wakeUp.start()
    }
    fun stopVoiceWakeUp() {
        wakeUp.stop()
    }
    fun startVoiceRec() {
        tts.stop()
        stopVoiceWakeUp()
        v!!.onGetDialogIntent(VoiceRec.getDialogIntent())
    }

    override fun onReadyForSpeech(bundle: Bundle) {

    }

    override fun onBeginningOfSpeech() {

    }

    override fun onRmsChanged(v: Float) {
        this.v!!.onRmsChanged(v)
    }

    override fun onBufferReceived(bytes: ByteArray) {

    }

    override fun onEndOfSpeech() {

    }

    override fun onError(i: Int) {

    }

    override fun onResults(bundle: Bundle) {
        val raw = BaiduUtil.getRecResult(bundle)
        val result = StringUtil.ridPunctuation(raw)
        sendRightMsg(result)
        handleResult(userPurpose.getUserCommand(result))

    }

    fun sendLeftMsg(msg: String, isSpeak: Boolean) {
        val msgs = Message(msg, 0)
        adapter.addMessage(msgs)
        if (isSpeak) {
            tts.speak(msg)
        }
        v!!.smoothBottom()
    }

    fun sendRightMsg(msg: String) {
        v!!.smoothBottom()
        adapter.addMessage(Message(msg, 1))
    }

    fun handleResult(userCommand: UserCommand) {
        val target = userCommand.sentence.target
        when (userCommand.type) {
            UserCommand.COMMAND_CALL_PHONE  // 打电话
            -> {
                if (phoneInfos == null || phoneInfos!!.size == 0) {
                    phoneInfos = PhoneUtil.getPhoneInfos(context)
                }
                val result = PhoneUtil.getPhonesByName(phoneInfos, target)
                if (result.size > 0) {
                    sendLeftMsg("正在为您呼叫: " + result[0].name, false)
                    PhoneUtil.fastCallPerson(context, result[0].number)
                } else {
                    sendLeftMsg("未找到联系人:" + target, true)
                }
            }
            UserCommand.COMMAND_OPEN_APP// 打开APP
            -> {
                if (packageInfos == null || packageInfos!!.size == 0) {
                    packageInfos = Apk.getPackagesInfos(context)
                }
                var packName = Apk.getAppPackageName(context, packageInfos, target)
                if (packName != Flag.FAIL) {
                    sendLeftMsg("正在打开：" + target, false)
                    Apk.openApp(context, packName)
                } else {
                    sendLeftMsg("未找到应用：" + target, true)
                }
            }
            UserCommand.COMMAND_DELETE_APP // 卸载APP
            -> {
                if (packageInfos == null || packageInfos!!.size == 0) {
                    packageInfos = Apk.getPackagesInfos(context)
                }
                val packName = Apk.getAppPackageName(context, packageInfos, target)
                if (packName != Flag.FAIL) {
                    sendLeftMsg("正在卸载：" + target, false)
                    Apk.unInstallApp(context, packName)

                } else {
                    sendLeftMsg("未找到应用：" + target, true)
                }
            }
            UserCommand.COMMAND_SEARCH // 网络搜索
            -> {
                var intent = Intent(context, SearchActivity::class.java)
                intent.putExtra("keyword", target)
                context.startActivity(intent)
            }
            UserCommand.COMMAND_REMIND// 设置提醒
            -> {
                val intent = Intent(context, EditRemindActivity::class.java)
                intent.putExtra("isNew", true)
                intent.putExtra("remindContent", userCommand.sentence.target)
                intent.putExtra("similarTime", userCommand.sentence.qualifier)
                context.startActivity(intent)
            }
            UserCommand.COMMAND_CHAT -> {
                val rx = ApiManage.getInstence().tuLingService.getResult(com.sunmoon.helper.common.TuLing.API_KEY, target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ tuLing -> sendLeftMsg(tuLing.text, true) }) { throwable ->
                            sendLeftMsg(throwable.message!!, false)
                        }
            }
        }
    }

    override fun onPartialResults(bundle: Bundle) {

    }

    override fun onEvent(i: Int, bundle: Bundle) {

    }
}
