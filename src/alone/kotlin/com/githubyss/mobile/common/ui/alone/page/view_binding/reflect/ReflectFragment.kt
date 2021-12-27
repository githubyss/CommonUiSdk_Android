package com.githubyss.mobile.common.ui.alone.page.view_binding.reflect

import com.githubyss.mobile.common.ui.base.view_binding.page.reflect.BaseFragment
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingReflectBinding


/**
 * ReflectFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/21 11:16:42
 */
class ReflectFragment : BaseFragment<ComuiFragmentViewBindingReflectBinding>() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = ReflectFragment::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        binding.textBindingReflect.setOnClickListener {}
    }
}
