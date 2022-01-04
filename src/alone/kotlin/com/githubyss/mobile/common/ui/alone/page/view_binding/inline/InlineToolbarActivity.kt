package com.githubyss.mobile.common.ui.alone.page.view_binding.inline

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseInlineToolbarActivity


/**
 * InlineToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:55:13
 */
class InlineToolbarActivity : BaseInlineToolbarActivity() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = InlineToolbarActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(InlineToolbarFragment(), InlineToolbarFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_view_binding_toolbar_inline_title)
    }
}
