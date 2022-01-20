package com.githubyss.mobile.common.ui.app.application

import android.app.Application
import android.content.Context
import com.githubyss.mobile.common.kit.manager.speech_recognition.SpeechRecognitionManager
import com.githubyss.mobile.common.net.ComnetApplicationConfig
import com.githubyss.mobile.common.kit.base.application.BaseApplication
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
        private var instance: ComuiApplication by Delegates.notNull()
            private set

        private val TAG: String = ComuiApplication::class.java.simpleName

        fun getApp(): ComuiApplication {
            return instance
        }
    }


    /** ****************************** Override ****************************** */

    override fun onCreate() {
        super.onCreate()
        instance = this

        initSpeechRecognition(instance)
    }


    /** ****************************** Open ****************************** */

    override fun initComnet(application: Application) {
        super.initComnet(application)
        ComnetApplicationConfig.init(application)
    }


    /** ****************************** Functions ****************************** */

    private fun initSpeechRecognition(context: Context) {
        SpeechRecognitionManager.initSdk(context)
    }
}
