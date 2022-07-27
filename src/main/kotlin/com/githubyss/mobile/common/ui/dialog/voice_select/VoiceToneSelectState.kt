package com.githubyss.mobile.common.ui.dialog.voice_select

import androidx.annotation.StringDef


/**
 * VoiceTone
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/26 22:54:29
 */
@StringDef(VoiceToneSelectState.NO, VoiceToneSelectState.YES)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class VoiceToneSelectState {
    companion object {
        const val NO = "0"
        const val YES = "1"
    }
}
