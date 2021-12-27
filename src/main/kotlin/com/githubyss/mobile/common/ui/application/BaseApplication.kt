package com.githubyss.mobile.common.ui.application

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.BuildConfig
import kotlin.properties.Delegates


abstract class BaseApplication : Application() {

    /** ****************************** Properties ****************************** */

    companion object {
        var instance: BaseApplication by Delegates.notNull()
            private set

        private val TAG: String = BaseApplication::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun onCreate() {
        super.onCreate()
        instance = this
    
        initComkit(instance)
        initComnet(instance)
        initLog(LogUtils.LOG_LEVEL_VERBOSE)
        registerActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
    }
    
    override fun onTerminate() {
        unregisterActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
        ActivityUtils.activityLifecycle.activityList.clear()
        super.onTerminate()
    }
    

    /** ****************************** Open ****************************** */

    open fun initComkit(application: Application) {
        ComkitApplicationConfig.init(application)
    }

    open fun initComnet(application: Application) {
        // ComnetApplicationConfig.init(application)
    }
    
    open fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }
    
    open fun initLog(level: Int) {
        LogUtils.logLevel = level
    }
}
