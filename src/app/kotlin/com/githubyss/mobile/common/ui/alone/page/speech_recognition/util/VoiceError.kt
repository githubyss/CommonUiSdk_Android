package com.githubyss.mobile.common.ui.alone.page.speech_recognition.util

import android.util.SparseArray


object VoiceError {
    const val ERROR_NO_ERROR = 0
    const val ERROR_CANNNOT_UNDERSTAND = 20009
    const val ERROR_NO_SPEAKING = 20008
    const val ERROR_UNKNOWN = -10869703
    const val ERROR_NO_NETWORK = -10869704
    const val ERROR_NETWORK_EXCEPTION = -10869705
    const val ERROR_NO_LOGIN = -10869706
    const val DEFAULT_ANSWER = "“对不起，您说的太高深了，\n" +
            "我理解不了。我尽快掌握技\n" +
            "能，为您提供更好服务”"
    val MAP_ERROR_CODE = SparseArray<String>(0)
    fun getErrorMsg(errorCode: Int): String {
        return MAP_ERROR_CODE[errorCode, DEFAULT_ANSWER]
    }

    init {
        MAP_ERROR_CODE.put(ERROR_NO_ERROR, "操作成功。")
        MAP_ERROR_CODE.put(ERROR_CANNNOT_UNDERSTAND, DEFAULT_ANSWER)
        MAP_ERROR_CODE.put(ERROR_NO_SPEAKING, "您好像没有讲话")
        MAP_ERROR_CODE.put(ERROR_UNKNOWN, "网络不给力，请稍候再试！")
        MAP_ERROR_CODE.put(ERROR_NO_NETWORK, "无网络。")
        MAP_ERROR_CODE.put(ERROR_NETWORK_EXCEPTION, "网络异常。")
        MAP_ERROR_CODE.put(ERROR_NO_LOGIN, "未登录。")
    }
}