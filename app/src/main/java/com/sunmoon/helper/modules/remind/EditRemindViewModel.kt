package com.sunmoon.helper.modules.remind

import android.content.Context

import android.databinding.ObservableField
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup

import com.sunmoon.helper.base.BaseViewModel
import com.sunmoon.helper.model.Remind

import org.joda.time.DateTime
import org.joda.time.DateTimeFieldType

/**
 * Created by SunMoon on 2017/5/16.
 */

class EditRemindViewModel : BaseViewModel() {
    private val hour = ObservableField(12)
    private val minute = ObservableField(0)
    private var remindId: Long = 0
    private var remind: Remind? = null
    var remindContent: String? = null
    /**
     * @param id 提醒id
     * 编辑已存在的提醒
     */
    fun init() {
        this
    }
    fun initById(id: Long) {
        this.remindId = id
        this.remindContent = remind!!.content
        val dt = DateTime(remind!!.time)
        this.hour!!.set(dt.hourOfDay)
        this.minute!!.set(dt.minuteOfHour)
    }

    /**
     * @param similarTime 与时间相似的文字，示例: 22点30, 十二点30
     * @param remindContent 提醒内容
     * 添加新的提醒
     */
    fun initNew(similarTime: String, remindContent: String) {
        remind = Remind()
        remind!!.content = remindContent
        this.remindContent = remindContent
        val dt = getDateTime(similarTime)
        this.hour!!.set(dt.hourOfDay)
        this.minute!!.set(dt.minuteOfHour)
    }
    fun confirmRemind() {
        if (remindContent == "" || remindContent == null) {
            com.sunmoon.helper.utils.Toast.show(context, "提醒内容不能为空！")
            return
        }
        remind!!.content = remindContent
        val dateTime = DateTime().withField(DateTimeFieldType.clockhourOfDay(), hour!!.get()!!).withField(DateTimeFieldType.minuteOfHour(), minute!!.get()!!)
        remind!!.time = dateTime.toString()
        if (remindId == 0L) {
            remind!!.id = System.currentTimeMillis()

        } else {

        }
    }


    fun getDateTime(similarTime: String): DateTime {
        try {
            val timePoint = arrayOf("点", ":", "：")
            var hStr = ""
            var mStr = ""
            for (s in timePoint) {
                if (similarTime.contains(s)) {
                    val temp = similarTime.split(s.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    hStr = temp[0]
                    if (temp.size == 1) {
                        mStr = "00"
                    } else {
                        mStr = temp[1]
                    }

                    break
                }
            }
            var h = hanZiToInt(hStr)
            var m = hanZiToInt(mStr)
            if (h > 23 || h < 0) {
                h = 12
            }
            if (m > 60 || m < 0) {
                m = 30
            }

            return DateTime().withField(DateTimeFieldType.clockhourOfDay(), h).withField(DateTimeFieldType.minuteOfHour(), m)
        } catch (e: Exception) {
            e.printStackTrace()
            return DateTime()

        }

    }

    /**
     * 汉字字符串转数字转数字字符串
     * 范伟00—60
     */
    fun hanZiToInt(hanZi: String): Int {
        val hanZis = hanZi.toCharArray()
        val res = intArrayOf(0, 0)
        val length = if (hanZis.size >= 3) 3 else hanZis.size
        for (i in hanZis.size - 1 downTo hanZis.size - length) {
            val raw = getNumberByChar(hanZis[i])
            if (raw == 10) {
                res[1] = 10
                continue
            }
            if (i == hanZis.size - 1) { // 个位
                res[0] = raw
            } else {
                res[1] = raw * 10
            }
        }
        return res[0] + res[1]
    }

    /**
     * 汉字字符串转数字转数字字符串
     * 范伟0—10
     */
    fun getNumberByChar(hanZi: Char): Int {
        val numbers = charArrayOf('零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十')
        for (i in numbers.indices) {
            if (hanZi == numbers[i] || hanZi.toInt() == 48 + i) {
                return i
            }
        }
        return 0
    }

    fun getHour(): Int {
        return hour!!.get()!!
    }

    fun setHour(hour: Int) {
        this.hour!!.set(hour)
    }

    fun getMinute(): Int {
        return minute!!.get()!!
    }

    fun setMinute(minute: Int) {
        this.minute!!.set(minute)
    }
}
