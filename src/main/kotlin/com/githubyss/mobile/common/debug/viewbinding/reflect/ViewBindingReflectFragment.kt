package com.githubyss.mobile.common.debug.viewbinding.reflect

import android.os.Bundle
import android.view.View
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.reflect.BaseToolbarFragmentBindingReflect
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingReflectBinding


/**
 * ViewBindingReflectFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:46:28
 */
class ViewBindingReflectFragment : BaseToolbarFragmentBindingReflect<ComuiFragmentViewBindingReflectBinding>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = ViewBindingReflectFragment::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        setToolbarTitle(R.string.comui_view_binding_reflect_title)
        
        binding.textBindingReflect.setOnClickListener(onClickListener)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
        }
    }
}
