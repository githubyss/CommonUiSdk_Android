package com.githubyss.mobile.common.ui.app.application

import android.content.Context
import com.githubyss.common.base.application.BaseApplication
import com.githubyss.mobile.common.kit.manager.speech_recognition.SpeechRecognitionManager
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

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ComuiApplication::class.java.simpleName

        fun getApp() = BaseApplication.instance
    }


    /** ****************************** Override ****************************** */

    override fun onCreate() {
        super.onCreate()
        instance = this

        initSpeechRecognition(instance)
    }


    /** ****************************** Open ****************************** */

    override fun initComnet() {
        ComnetApplicationConfig.init(application)
    }


    /** ****************************** Functions ****************************** */

    private fun initSpeechRecognition(context: Context) {
        SpeechRecognitionManager.initSdk(context)
    }
}
