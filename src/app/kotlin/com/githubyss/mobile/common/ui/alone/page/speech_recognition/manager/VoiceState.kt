package com.githubyss.mobile.common.ui.alone.page.speech_recognition.manager

import android.os.Handler

/**
 * Created by 16100260 on 2017/8/21.
 */
class VoiceState {
    var voiceStateTextCN: String
    var answerText: String
    var voiceKey: String? = null
    var answerAction: Runnable

    constructor(voiceStateText: String?) {
        voiceStateTextCN = "未知语音命令"
        answerText = "对不起，您说的太高深了，我理解不了。我会尽快掌握技能，为您提供更好服务"
        answerAction = Runnable { }
    }

    constructor(voiceKey: String?, voiceStateTextCN: String, answerText: String, answerAction: Runnable) {
        this.voiceKey = voiceKey
        this.voiceStateTextCN = voiceStateTextCN
        this.answerText = answerText
        this.answerAction = answerAction
    }

    constructor(voiceStateTextCN: String, answerText: String, answerAction: Runnable) {
        this.voiceStateTextCN = voiceStateTextCN
        this.answerText = answerText
        this.answerAction = answerAction
    }

    fun doAction(handler: Handler) {
        handler.postDelayed(answerAction, 1000)
    }
}