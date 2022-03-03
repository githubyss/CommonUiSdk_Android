package com.githubyss.mobile.common.ui.app.page.speech_recognition

import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarActivity
import com.githubyss.mobile.common.ui.R


/**
 * SpeechRecognitionActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/27 14:29:03
 */
class SpeechRecognitionActivity : BaseInlineBindingToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = SpeechRecognitionActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        switchFragment(SpeechRecognitionFragment(), SpeechRecognitionFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_speech_recognition_title)
    }
}
