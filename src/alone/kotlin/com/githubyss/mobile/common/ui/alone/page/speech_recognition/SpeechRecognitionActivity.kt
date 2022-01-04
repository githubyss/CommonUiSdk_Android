package com.githubyss.mobile.common.ui.alone.page.speech_recognition

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseInlineBindingToolbarActivity


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

    override fun init() {
        addFragment(SpeechRecognitionFragment(), SpeechRecognitionFragment.TAG, false, binding.layoutFragmentContainer.id)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_speech_recognition_title)
    }
}
