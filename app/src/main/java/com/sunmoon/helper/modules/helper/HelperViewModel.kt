package com.sunmoon.helper.modules.helper

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.databinding.ObservableField
import android.os.Build
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
import com.sunmoon.helper.utils.UserPurpose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sunmoon.voice.control.SpeechRec
import sunmoon.voice.tts.Speaker

/**
 * Created by SunMoon on 2016/11/30.
 */

@TargetApi(Build.VERSION_CODES.FROYO)
class HelperViewModel() : BaseViewModel() {
    private var curUserMsg: MyMessage? = null
    private var mPhoneList: List<Phone>? = null
    private var mPackageList: List<PackageInfo>? = null
    var adapter: MsgListAdapter<MyMessage>? = null
    val tip = ObservableField<String>("点击开始识别")
    val isSpeechModel = ObservableField<Boolean>(true)
    override fun init(context: Activity) {
        super.init(context)
        adapter = MsgListAdapter("0", object : ImageLoader {
            override fun loadAvatarImage(avatarImageView: ImageView?, string: String?) {
                Glide.with(context).load(string).into(avatarImageView)
            }
            override fun loadImage(imageView: ImageView?, string: String?) {
                Glide.with(context).load(string).into(imageView)
            }
        })
        mPhoneList = PhoneUtil.getPhones(context)
        mPackageList = Apk.getPackagesInfos(context)
    }
    fun start() {
        SpeechRec.getInstance().start()
    }
    fun sendMsg(msg: String) {
        val msg = MyMessage(msg, IMessage.MessageType.SEND_TEXT)
        msg.status = IMessage.MessageStatus.CREATED
        msg.setUserInfo(DefaultUser("1", "yonghu", "file:///android_asset/my_header.jpg"))
        curUserMsg = msg
        adapter?.addToStart(msg, true)
    }
    private fun replyMsg(msg: String) {
        if (curUserMsg != null) {
            curUserMsg?.status = IMessage.MessageStatus.SEND_SUCCEED
            adapter?.updateMessage(curUserMsg)
        }
        adapter?.addToStart(MyMessage(msg, IMessage.MessageType.RECEIVE_TEXT), true)
    }
    fun execCommand(content: String) {
        sendMsg(content)
        val userCommand = UserPurpose.create(content)
        val target = userCommand.sentence.target
        when (userCommand.type) {
            UserCommand.COMMAND_CALL_PHONE  // 打电话
            -> {
                val result = PhoneUtil.getPhonesByName(mPhoneList, target)
                if (result.size > 0) {
                    replyMsg("正在为您呼叫: " + result[0].name)
                    if (!PhoneUtil.fastCallPerson(context, result[0].number)){
                        PhoneUtil.callPerson(context,result[0].number)
                    }
                } else {
                    replyMsg("未找到联系人:" + target)
                }
            }
            UserCommand.COMMAND_OPEN_APP// 打开APP
            -> {
                var packName = Apk.getAppPackageName(context, mPackageList, target)
                if (packName != Flag.FAIL) {
                    replyMsg("正在打开：" + target)
                    Apk.openApp(context, packName)
                } else {
                    replyMsg("未找到应用：" + target)
                }
            }
            UserCommand.COMMAND_DELETE_APP // 卸载APP
            -> {
                val packName = Apk.getAppPackageName(context, mPackageList, target)
                if (packName != Flag.FAIL) {
                    replyMsg("正在卸载：" + target)
                    Apk.unInstallApp(context, packName)

                } else {
                    replyMsg("未找到应用：" + target)
                }
            }
            UserCommand.COMMAND_SEARCH // 网络搜索
            -> {
                replyMsg("成功为您搜索："+ target)
                var intent = Intent(context, SearchActivity::class.java)
                intent.putExtra("keyword", target)
                context?.startActivity(intent)
            }
            UserCommand.COMMAND_REMIND // 添加提醒
            -> {
                val intent = Intent(context, EditRemindActivity::class.java)
                intent.putExtra("isNew", true)
                intent.putExtra("remindContent", userCommand.sentence.target)
                intent.putExtra("similarTime", userCommand.sentence.qualifier)
                context?.startActivity(intent)
            }
            UserCommand.COMMAND_CHAT -> {
                val rx = ApiManage.getInstence().tuLing.getResult(com.sunmoon.helper.common.TuLing.API_KEY, target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ tuLing ->
                            replyMsg(tuLing.text)
                            if (HelperSetting.canSpeak) {
                                Speaker.getInstance().start(tuLing.text)
                            }
                        }) { throwable ->
                            replyMsg(throwable.message!!)

                        }

            }
        }
    }
}
