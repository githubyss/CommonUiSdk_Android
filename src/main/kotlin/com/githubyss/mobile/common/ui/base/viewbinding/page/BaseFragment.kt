package com.githubyss.mobile.common.ui.base.viewbinding.page

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.debug.application.ComuiApplication
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.ui.R


/**
 * BaseFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:11:59
 */
abstract class BaseFragment(@LayoutRes layoutId: Int = 0) : Fragment(layoutId) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = BaseFragment::class.simpleName ?: "simpleName is null"
    }
    
    var fragmentContext: Context? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        LogcatUtils.d(TAG, "Constructor init")
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentContext = activity?.baseContext ?: ComuiApplication.instance
    }
    
    
    /** ********** ********** ********** Open ********** ********** ********** */
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Change button status. */
    protected fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }
    
    /** Add fragment to activity. */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.frameLayoutFragmentContainer) {
        fragmentManager?.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }
            
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.add(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
    
    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.frameLayoutFragmentContainer) {
        fragmentManager?.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }
            
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
