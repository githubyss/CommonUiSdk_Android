package com.githubyss.mobile.common.ui.alone.page.view_binding.reflect

import com.githubyss.mobile.common.ui.base.view_binding.page.reflect.BaseReflectActivity
import com.githubyss.mobile.common.ui.databinding.ComuiActivityBaseBinding


/**
 * ReflectActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 18:01:36
 */
class ReflectActivity : BaseReflectActivity<ComuiActivityBaseBinding>() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = ReflectActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(ReflectToolbarFragment(), ReflectToolbarFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
}
