package com.githubyss.mobile.common.ui.application

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.network.ComnetApplicationConfig
import com.githubyss.mobile.common.ui.BuildConfig
import kotlin.properties.Delegates


open class BaseApplication : Application() {

    /** ********** ********** ********** Companion ********** ********** ********** */

    companion object {
        var instance: BaseApplication by Delegates.notNull()
            private set

        private val TAG = BaseApplication::class.simpleName ?: "simpleName is null"
    }


    /** ********* ********** ********** Override ********** ********** ********** */

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    

    /** ********* ********** ********** Open ********** ********** ********** */

    open fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }

    open fun initComkit(application: Application) {
        ComkitApplicationConfig.init(application)
    }

    open fun initComnet(application: Application) {
        ComnetApplicationConfig.init(application)
    }

    open fun initLog(level: Int) {
        LogcatUtils.logLevel = level
    }
}
