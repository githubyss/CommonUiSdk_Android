package com.githubyss.mobile.common.ui.app.page.speech_recognition

import com.githubyss.common.base.activity_fragment.binding_inline.BaseInlineBindingToolbarActivity
import com.githubyss.common.base.activity_fragment.binding_inline_root.inflate
import com.githubyss.common.base.databinding.CombaseActivityBaseToolbarBinding
import com.githubyss.mobile.common.ui.R


/**
 * SpeechRecognitionActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/27 14:29:03
 */
class SpeechRecognitionActivity : BaseInlineBindingToolbarActivity<CombaseActivityBaseToolbarBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = SpeechRecognitionActivity::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val _binding by inflate<CombaseActivityBaseToolbarBinding>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        binding = _binding
        switchFragment(SpeechRecognitionFragment(), SpeechRecognitionFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_speech_recognition_title)
    }
}
