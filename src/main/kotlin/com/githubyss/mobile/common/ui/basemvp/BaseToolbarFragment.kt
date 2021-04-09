package com.githubyss.mobile.common.ui.basemvp

import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding


/**
 * BaseToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseToolbarFragment<VB : ViewBinding> : BaseFragment<VB>() {
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseToolbarActivity<*> && (activity as BaseToolbarActivity<*>).binding is ComuiActivityBaseBinding) {
            ((activity as BaseToolbarActivity<*>).binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }
    
    /** Setup Toolbar text by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseToolbarActivity<*> && (activity as BaseToolbarActivity<*>).binding is ComuiActivityBaseBinding) {
            ((activity as BaseToolbarActivity<*>).binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
