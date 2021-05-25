package com.githubyss.mobile.common.debug.homepage

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.baseviewbindingpage.BaseToolbarViewBindingActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingAudioPlayer
import com.githubyss.mobile.common.ui.floatingview.container.app.AppFloatingIcon


/**
 * ComuiHomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class ComuiHomepageActivity : BaseToolbarViewBindingActivity<ComuiActivityBaseBinding>() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        private val TAG = ComuiHomepageActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        addFragment(ComuiHomepageFragment(), TAG, false, binding.layoutFragmentContainer.id)
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
