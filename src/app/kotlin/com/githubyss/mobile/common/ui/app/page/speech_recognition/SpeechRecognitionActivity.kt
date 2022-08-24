package com.githubyss.mobile.common.ui.app.page.speech_recognition

import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarActivity
import com.githubyss.common.base.databinding.CombaseActivityBaseToolbarBinding
import com.githubyss.mobile.common.ui.R


/**
 * SpeechRecognitionActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/27 14:29:03
 */
class SpeechRecognitionActivity : BaseReflectBindingToolbarActivity<CombaseActivityBaseToolbarBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = SpeechRecognitionActivity::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        switchFragment(SpeechRecognitionFragment(), SpeechRecognitionFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_speech_recognition_title)
    }
}
