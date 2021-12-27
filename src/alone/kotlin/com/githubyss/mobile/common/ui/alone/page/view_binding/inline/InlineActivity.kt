package com.githubyss.mobile.common.ui.alone.page.view_binding.inline

import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseActivity


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
        private val TAG: String = InlineActivity::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        addFragment(InlineFragment(), InlineFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
}
