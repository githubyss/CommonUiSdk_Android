package com.githubyss.mobile.common.kit.application

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.network.ComnetApplicationConfig
import com.githubyss.mobile.common.ui.BuildConfig
import kotlin.properties.Delegates


open class BaseApplication : Application() {
    companion object {
        var instance: BaseApplication by Delegates.notNull()
            private set
        
        private val TAG = BaseApplication::class.simpleName
    }
    
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        initARouter(instance)
        initComkit(instance)
        initComnet(instance)
        initLog(LogcatUtils.LOG_LEVEL_VERBOSE)
    }
    
    private fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }
    
    private fun initComkit(application: Application) {
        ComkitApplicationConfig.init(application)
    }
    
    private fun initComnet(application: Application) {
        ComnetApplicationConfig.init(application)
    }
    
    private fun initLog(level: Int) {
        LogcatUtils.logLevel = level
    }
}
