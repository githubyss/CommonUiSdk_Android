package com.githubyss.mobile.common.debug.homepage

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarActivity
import com.githubyss.mobile.common.ui.floating_view.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floating_view.container.app.AppFloatingIcon


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class HomepageActivity : BaseToolbarActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = HomepageActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        addFragment(HomepageFragment(), HomepageFragment.TAG, false, binding.frameFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_homepage)
    }
    
    override fun onStart() {
        super.onStart()
        AppFloatingIcon.getInstance(this)
            .attach(this)
        AppFloatingAudioPlayer.getInstance(this)
            .attach(this)
    }
    
    override fun onStop() {
        super.onStop()
        AppFloatingIcon.getInstance(this)
            .detach(this)
        AppFloatingAudioPlayer.getInstance(this)
            .detach(this)
    }
}
