package com.githubyss.mobile.common.debug.viewbinding.reflect

import android.os.Bundle
import com.githubyss.mobile.common.ui.base.viewbinding.page.reflect.BaseActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * ReflectActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 18:01:36
 */
class ReflectActivity : BaseActivity<ComuiActivityBaseToolbarBinding>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = ReflectActivity::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(ViewBindingReflectFragment(), ViewBindingReflectFragment.TAG, false, binding.frameLayoutFragmentContainer.id)
    }
}
