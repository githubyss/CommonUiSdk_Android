package com.githubyss.mobile.common.ui.app.page.homepage

import android.os.Bundle
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarActivity
import com.githubyss.mobile.common.ui.floating_view.container.app.AppFloatingAudioPlayer


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class HomepageActivity : BaseInlineBindingToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        addFragment(HomepageFragment(), HomepageFragment.TAG, false, binding.layoutFragmentContainer.id)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_homepage_title)
    }

    override fun onStart() {
        super.onStart()
        attachView()
    }

    override fun onStop() {
        super.onStop()
        detachView()
    }


    /** ****************************** Functions ****************************** */

    private fun attachView() {
        // AppFloatingIcon.getInstance(this)
        //     .attach(this)
        AppFloatingAudioPlayer.getInstance(this)
            .attach(this)
    }

    private fun detachView() {
        // AppFloatingIcon.getInstance(this)
        //     .detach(this)
        AppFloatingAudioPlayer.getInstance(this)
            .detach(this)
    }
}
