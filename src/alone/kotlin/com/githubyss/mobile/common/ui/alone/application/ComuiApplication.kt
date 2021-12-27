package com.githubyss.mobile.common.ui.alone.application

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
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        var instance: ComuiApplication by Delegates.notNull()
            private set
        
        private val TAG: String = ComuiApplication::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        initARouter(instance)
    }
}
