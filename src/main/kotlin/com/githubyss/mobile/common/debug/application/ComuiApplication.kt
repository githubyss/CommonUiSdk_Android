package com.githubyss.mobile.common.debug.application

import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.ui.application.BaseApplication
import kotlin.properties.Delegates


/**
 * ComuiApplication
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:16:21
 */
class ComuiApplication : BaseApplication() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        var instance: ComuiApplication by Delegates.notNull()
            private set
        
        private val TAG = ComuiApplication::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate() {
        super.onCreate()
        instance = this

        initARouter(instance)
        initComkit(instance)
        initComnet(instance)
        initLog(LogcatUtils.LOG_LEVEL_VERBOSE)
    }
}
