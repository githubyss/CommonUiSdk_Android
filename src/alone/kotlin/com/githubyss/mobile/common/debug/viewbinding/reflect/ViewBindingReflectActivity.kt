package com.githubyss.mobile.common.debug.viewbinding.reflect

import android.os.Bundle
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.reflect.BaseToolbarActivityBindingReflect
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * ViewBindingReflectActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:17:26
 */
class ViewBindingReflectActivity : BaseToolbarActivityBindingReflect<ComuiActivityBaseToolbarBinding>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = ViewBindingReflectActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(ViewBindingReflectFragment(), ViewBindingReflectFragment.TAG, false, binding.frameLayoutFragmentContainer.id)
    }
    
    override fun onResume() {
        super.onResume()
        setToolbarTitle(R.string.comui_view_binding_reflect_title)
    }
}
