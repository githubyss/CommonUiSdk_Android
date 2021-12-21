package com.githubyss.mobile.common.ui.base.view_binding.page.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.R


/**
 * BaseActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:10:38
 */
abstract class BaseActivity : AppCompatActivity() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = BaseActivity::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        LogUtils.d(TAG, "Constructor init")
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.let {
            it.registerFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle, true)
        }
        init()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.let {
            it.unregisterFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle)
        }
    }
    
    
    /** ********** ********** ********** Abstract ********** ********** ********** */
    
    abstract fun init()
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** Add fragment to activity. */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragment_container) {
        supportFragmentManager.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }
            
            fragment.arguments = intent.extras
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.add(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
    
    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragment_container) {
        supportFragmentManager.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }
            
            fragment.arguments = intent.extras
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
