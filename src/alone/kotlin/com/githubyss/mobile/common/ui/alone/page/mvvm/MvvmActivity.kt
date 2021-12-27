package com.githubyss.mobile.common.ui.alone.page.mvvm

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarActivity


/**
 * MvvmActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 10:56:07
 */
class MvvmActivity : BaseToolbarActivity() {
    
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
