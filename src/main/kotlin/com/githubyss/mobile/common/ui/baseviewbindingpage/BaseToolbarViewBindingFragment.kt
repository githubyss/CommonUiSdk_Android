package com.githubyss.mobile.common.ui.baseviewbindingpage

import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding


/**
 * BaseToolbarViewBindingFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseToolbarViewBindingFragment<VB : ViewBinding> : BaseViewBindingFragment<VB>() {
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Setup Toolbar text by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseToolbarViewBindingActivity<*> && (activity as BaseToolbarViewBindingActivity<*>).binding is ComuiActivityBaseBinding) {
            ((activity as BaseToolbarViewBindingActivity<*>).binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }
    
    /** Setup Toolbar text by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseToolbarViewBindingActivity<*> && (activity as BaseToolbarViewBindingActivity<*>).binding is ComuiActivityBaseBinding) {
            ((activity as BaseToolbarViewBindingActivity<*>).binding as ComuiActivityBaseBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
