package com.githubyss.mobile.common.ui.base.viewbinding.page.inline

import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.ui.base.viewbinding.page.base.BaseFragment
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * BaseToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:52:19
 */
abstract class BaseToolbarFragment(@LayoutRes layoutId: Int) : BaseFragment(layoutId) {
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }
    
    
    /** ********** ********** ********** Abstract ********** ********** ********** */
    
    abstract fun setToolbarTitle()
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseToolbarActivity && (activity as BaseToolbarActivity).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseToolbarActivity).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }
    
    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseToolbarActivity && (activity as BaseToolbarActivity).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseToolbarActivity).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
