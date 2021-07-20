package com.githubyss.mobile.common.debug.viewbinding.inline

import android.os.Bundle
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarActivityBindingInline


/**
 * ViewBindingInlineActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:55:13
 */
class ViewBindingInlineActivity : BaseToolbarActivityBindingInline() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = ViewBindingInlineActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(ViewBindingInlineFragment(), ViewBindingInlineFragment.TAG, false, binding.frameLayoutFragmentContainer.id)
    }
    
    override fun onResume() {
        super.onResume()
        setToolbarTitle(R.string.comui_view_binding_inline_title)
    }
}
