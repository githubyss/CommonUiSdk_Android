package com.githubyss.mobile.common.ui.app.page.mvvm

import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarActivity
import com.githubyss.mobile.common.ui.R


/**
 * MvvmActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 10:56:07
 */
class MvvmActivity : BaseInlineBindingToolbarActivity() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = MvvmActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(MvvmFragment(), MvvmFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_mvvm_title)
    }
}
