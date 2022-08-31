package com.githubyss.mobile.common.ui.app.application

import android.content.Context
import com.github.sahasbhop.apngview.ApngImageLoader
import com.githubyss.common.base.application.BaseApplication
import com.githubyss.mobile.common.kit.BuildConfig
import com.githubyss.mobile.common.kit.manager.speech_recognition.SpeechRecognitionManager
import com.githubyss.mobile.common.kit.util.LOG_LEVEL_VERBOSE
import com.githubyss.mobile.common.kit.util.disableLog
import com.githubyss.mobile.common.kit.util.enableLog
import com.githubyss.mobile.common.kit.util.logLevel
import com.githubyss.mobile.common.net.ComnetApplicationConfig
import com.githubyss.mobile.common.net.ComnetApplicationConfig.application


/**
 * ComuiApplication
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 11:16:21
 */
class ComuiApplication : BaseApplication() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        private val TAG: String = ComuiApplication::class.java.simpleName
        fun getApp() = instance
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate() {
        super.onCreate()
        instance = this

        ApngImageLoader.getInstance().init(applicationContext);
        initSpeechRecognition(instance)
    }

    /**  */
    override fun initComnet() {
        ComnetApplicationConfig.init(application)
    }

    /**  */
    override fun initLog() {
        // 可调试模式，启用日志
        if (BuildConfig.DEBUG) {
            enableLog()
        }
        else {
            disableLog()
        }
        logLevel = LOG_LEVEL_VERBOSE
    }


    /** ****************************** Functions ****************************** */

    /**  */
    private fun initSpeechRecognition(context: Context) {
        SpeechRecognitionManager.initSdk(context)
    }
}
