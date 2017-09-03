package com.sunmoon.helper.modules.helper

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.widget.ImageView
import cn.jiguang.imui.commons.ImageLoader
import cn.jiguang.imui.commons.models.IMessage
import cn.jiguang.imui.messages.MsgListAdapter
import com.bumptech.glide.Glide
import com.sunmoon.helper.api.ApiManage
import com.sunmoon.helper.base.BaseViewModel
import com.sunmoon.helper.common.Flag
import com.sunmoon.helper.model.DefaultUser
import com.sunmoon.helper.model.MyMessage
import com.sunmoon.helper.model.Phone
import com.sunmoon.helper.model.UserCommand
import com.sunmoon.helper.modules.remind.EditRemindActivity
import com.sunmoon.helper.modules.search.SearchActivity
import com.sunmoon.helper.utils.Apk
import com.sunmoon.helper.utils.PhoneUtil
import com.sunmoon.helper.utils.StringUtil
import com.sunmoon.helper.utils.UserPurpose
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import sunmoon.voice.recognition.BaiduUtil
import sunmoon.voice.recognition.VoiceRec
import sunmoon.voice.recognition.VoiceWakeUp
import sunmoon.voice.recognition.WakeUpListener
import sunmoon.voice.tts.Tts


/**
 * Created by SunMoon on 2016/11/30.
 */

@TargetApi(Build.VERSION_CODES.FROYO)
class HelperViewModel(private val context: Context) : BaseViewModel(), RecognitionListener {
    private val userPurpose: UserPurpose = UserPurpose()
    private var packageInfos: List<PackageInfo>? = null
    private var phoneInfos: List<Phone>? = null
    private val tts = Tts()
    private val wakeUp: VoiceWakeUp
    private val REC_CODE = 1// 语音识别
    private var v: ChatView? = null
    private var curUserMsg:MyMessage ?= null
     var adapter: MsgListAdapter<MyMessage>
    init {

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
        // 消息
        adapter = MsgListAdapter("0", object : ImageLoader {
            override fun loadAvatarImage(avatarImageView: ImageView?, string: String?) {
                Glide.with(context).load(string).into(avatarImageView)
            }

            override fun loadImage(imageView: ImageView?, string: String?) {
                Glide.with(context).load(string).into(imageView)
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
        sendUserMessage(result)
        handleResult(userPurpose.getUserCommand(result))

    }

    fun sendReplyMsg(msg: String, isSpeak: Boolean) {
        if (isSpeak) {
            tts.speak(msg)
        }
        if (curUserMsg != null){
            curUserMsg?.status = IMessage.MessageStatus.SEND_SUCCEED
            adapter.updateMessage(curUserMsg)
        }
        adapter.addToStart(MyMessage(msg,IMessage.MessageType.RECEIVE_TEXT), true)
    }

    fun sendUserMessage(msg: String) {
        val msg = MyMessage(msg, IMessage.MessageType.SEND_TEXT)
        msg.status = IMessage.MessageStatus.CREATED
        msg.setUserInfo(DefaultUser("1", "yonghu", "file:///android_asset/my_header.jpg"))

        curUserMsg = msg
        adapter.addToStart(msg, true)
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
                    sendReplyMsg("正在为您呼叫: " + result[0].name, false)
                    PhoneUtil.fastCallPerson(context, result[0].number)
                } else {
                    sendReplyMsg("未找到联系人:" + target, true)
                }
            }
            UserCommand.COMMAND_OPEN_APP// 打开APP
            -> {
                if (packageInfos == null || packageInfos!!.size == 0) {
                    packageInfos = Apk.getPackagesInfos(context)
                }
                var packName = Apk.getAppPackageName(context, packageInfos, target)
                if (packName != Flag.FAIL) {
                    sendReplyMsg("正在打开：" + target, false)
                    Apk.openApp(context, packName)
                } else {
                    sendReplyMsg("未找到应用：" + target, true)
                }
            }
            UserCommand.COMMAND_DELETE_APP // 卸载APP
            -> {
                if (packageInfos == null || packageInfos!!.size == 0) {
                    packageInfos = Apk.getPackagesInfos(context)
                }
                val packName = Apk.getAppPackageName(context, packageInfos, target)
                if (packName != Flag.FAIL) {
                    sendReplyMsg("正在卸载：" + target, false)
                    Apk.unInstallApp(context, packName)

                } else {
                    sendReplyMsg("未找到应用：" + target, true)
                }
            }
            UserCommand.COMMAND_SEARCH // 网络搜索
            -> {
                sendReplyMsg("成功为您搜索："+ target, false)
                var intent = Intent(context, SearchActivity::class.java)
                intent.putExtra("keyword", target)
                context.startActivity(intent)
            }
            UserCommand.COMMAND_REMIND // 添加提醒
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
                        .subscribe({ tuLing -> sendReplyMsg(tuLing.text, true) }) { throwable ->
                            sendReplyMsg(throwable.message!!, false)
                        }
            }
        }
    }

    override fun onPartialResults(bundle: Bundle) {

    }

    override fun onEvent(i: Int, bundle: Bundle) {

    }
}
