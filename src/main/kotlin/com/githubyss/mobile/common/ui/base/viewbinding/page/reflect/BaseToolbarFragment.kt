package com.githubyss.mobile.common.ui.base.viewbinding.page.reflect

import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * BaseToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseToolbarFragment<B : ViewBinding> : BindingReflectBaseFragment<B>() {
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseToolbarActivity<*> && (activity as BaseToolbarActivity<*>).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseToolbarActivity<*>).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }
    
    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseToolbarActivity<*> && (activity as BaseToolbarActivity<*>).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseToolbarActivity<*>).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
