package com.githubyss.mobile.common.debug.view_binding.inline

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarActivity


/**
 * InlineToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:55:13
 */
class InlineToolbarActivity : BaseToolbarActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = InlineToolbarActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        addFragment(InlineToolbarFragment(), InlineToolbarFragment.TAG, false, binding.frameFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_view_binding_inline)
        // setToolbarTitle("Inline Toolbar Activity")
    }
}
