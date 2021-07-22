package com.githubyss.mobile.common.debug.viewbinding.reflect

import android.os.Bundle
import android.view.View
import com.githubyss.mobile.common.ui.base.viewbinding.page.reflect.BaseFragment
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingReflectBinding


/**
 * ReflectFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/21 11:16:42
 */
class ReflectFragment : BaseFragment<ComuiFragmentViewBindingReflectBinding>() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = ReflectFragment::class.java.simpleName
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textBindingReflect.setOnClickListener {}
    }
}
