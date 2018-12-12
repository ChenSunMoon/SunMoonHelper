package com.sunmoon.helper.base

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel

/**
 * Created by SunMoon on 2017/8/13.
 */

open class BaseViewModel : ViewModel(),LifecycleObserver{
    var context: Activity? = null
    var isLoaded = false
    open fun init(context: Activity) {
        this.context = context
    }
}
