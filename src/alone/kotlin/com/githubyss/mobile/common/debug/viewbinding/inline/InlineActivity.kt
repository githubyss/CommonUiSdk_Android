package com.githubyss.mobile.common.debug.viewbinding.inline

import android.os.Bundle
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseActivity


/**
 * InlineActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:42:44
 */
class InlineActivity : BaseActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = InlineActivity::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(InlineFragment(), InlineFragment.TAG, false, binding.frameLayoutFragmentContainer.id)
    }
}
