package com.githubyss.mobile.common.ui.base.viewbinding.page.reflect

import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * BaseToolbarFragmentBindingReflect
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseToolbarFragmentBindingReflect<B : ViewBinding> : BaseFragmentBindingReflect<B>() {
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseToolbarActivityBindingReflect<*> && (activity as BaseToolbarActivityBindingReflect<*>).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseToolbarActivityBindingReflect<*>).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }
    
    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseToolbarActivityBindingReflect<*> && (activity as BaseToolbarActivityBindingReflect<*>).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseToolbarActivityBindingReflect<*>).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
