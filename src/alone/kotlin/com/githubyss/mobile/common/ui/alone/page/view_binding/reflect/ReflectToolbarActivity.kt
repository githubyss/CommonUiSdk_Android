package com.githubyss.mobile.common.ui.alone.page.view_binding.reflect

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.reflect.BaseReflectToolbarActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseToolbarBinding


/**
 * ReflectToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:17:26
 */
class ReflectToolbarActivity : BaseReflectToolbarActivity<ComuiActivityBaseToolbarBinding>() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = ReflectToolbarActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(ReflectToolbarFragment(), ReflectToolbarFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_view_binding_toolbar_reflect_title)
    }
}
