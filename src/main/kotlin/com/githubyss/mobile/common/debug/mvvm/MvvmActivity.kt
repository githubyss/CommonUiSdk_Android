package com.githubyss.mobile.common.debug.mvvm

import android.os.Bundle
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarActivityBindingInline


/**
 * MvvmActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 10:56:07
 */
class MvvmActivity : BaseToolbarActivityBindingInline() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = MvvmActivity::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(MvvmFragment(), MvvmFragment.TAG, false, binding.frameLayoutFragmentContainer.id)
    }
    
    override fun onResume() {
        super.onResume()
        setToolbarTitle(R.string.comui_mvvm_title)
    }
}
