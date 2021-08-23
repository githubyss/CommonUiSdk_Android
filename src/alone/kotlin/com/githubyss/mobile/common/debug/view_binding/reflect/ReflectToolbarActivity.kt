package com.githubyss.mobile.common.debug.view_binding.reflect

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.reflect.BaseToolbarActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * ReflectToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:17:26
 */
class ReflectToolbarActivity : BaseToolbarActivity<ComuiActivityBaseToolbarBinding>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = ReflectToolbarActivity::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        addFragment(ReflectToolbarFragment(), ReflectToolbarFragment.TAG, false, binding.frameFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_view_binding_reflect)
        // setToolbarTitle("Reflect Toolbar Activity")
    }
}
