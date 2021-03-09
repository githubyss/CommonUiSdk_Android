package com.githubyss.mobile.common.debug.homepage

import android.os.Bundle
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.basemvp.BaseActivity


/**
 * ComuiHomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 10:54:13
 */
class ComuiHomepageActivity : BaseActivity() {
    
    /** ********** ********** ********** Companion ********** ********** ********** */
    
    companion object {
        private val TAG = ComuiHomepageActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********* ********** ********** Override ********** ********** ********** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(ComuiHomepageFragment(), TAG, false)
    }
    
    override fun onResume() {
        super.onResume()
        setToolbarTitle(R.string.comui_homepage_title)
    }
}
