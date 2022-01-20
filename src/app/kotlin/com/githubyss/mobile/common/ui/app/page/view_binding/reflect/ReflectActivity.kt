package com.githubyss.mobile.common.ui.app.page.view_binding.reflect

import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingActivity
import com.githubyss.mobile.common.kit.databinding.ComuiActivityBaseBinding


/**
 * ReflectActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 18:01:36
 */
class ReflectActivity : BaseReflectBindingActivity<ComuiActivityBaseBinding>() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = ReflectActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(ReflectToolbarFragment(), ReflectToolbarFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
}
