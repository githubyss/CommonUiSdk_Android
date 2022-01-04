package com.githubyss.mobile.common.ui.alone.page.view_binding.reflect

import android.view.View
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.reflect.BaseReflectToolbarFragment
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingReflectBinding


/**
 * ReflectToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:46:28
 */
class ReflectToolbarFragment : BaseReflectToolbarFragment<ComuiFragmentViewBindingReflectBinding>() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = ReflectToolbarFragment::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        initView()
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_view_binding_toolbar_reflect_title)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    private fun initView() {
        binding.textBindingReflect.setOnClickListener(onClickListener)
    }
    
    
    /** ****************************** Implementations ****************************** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
        }
    }
}
