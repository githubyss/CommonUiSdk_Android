package com.githubyss.mobile.common.ui.base.viewbinding.page.inline

import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.ui.base.viewbinding.page.BaseFragment


/**
 * BaseToolbarFragmentBindingInline
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:52:19
 */
abstract class BaseToolbarFragmentBindingInline(@LayoutRes layoutId: Int) : BaseFragment(layoutId) {

    /** ********** ********** ********** Functions ********** ********** ********** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseToolbarActivityBindingInline) {
            (activity as BaseToolbarActivityBindingInline).binding.toolbarBase.toolbarBase.setTitle(
                titleResId
            )
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseToolbarActivityBindingInline) {
            (activity as BaseToolbarActivityBindingInline).binding.toolbarBase.toolbarBase.title =
                titleString
        }
    }
}
