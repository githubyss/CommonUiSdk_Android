package com.githubyss.mobile.common.debug.homepage

import android.os.Bundle
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarActivityBindingInline
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingIcon


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class HomepageActivity : BaseToolbarActivityBindingInline() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = HomepageActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(HomepageFragment(), HomepageFragment.TAG, false, binding.frameLayoutFragmentContainer.id)
    }
    
    override fun onResume() {
        super.onResume()
        setToolbarTitle(R.string.comui_homepage_title)
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
