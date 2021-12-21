package com.githubyss.mobile.common.ui.alone.view_binding.inline

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
        private val TAG: String = InlineToolbarActivity::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        addFragment(InlineToolbarFragment(), InlineToolbarFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_view_binding_inline)
        // setToolbarTitle("Inline Toolbar Activity")
    }
}
