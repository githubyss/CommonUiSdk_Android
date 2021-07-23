package com.githubyss.mobile.common.debug.viewbinding.inline

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseFragment
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingInlineBinding


/**
 * InlineFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:44:22
 */
class InlineFragment : BaseFragment(R.layout.comui_fragment_view_binding_inline) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = InlineFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComuiFragmentViewBindingInlineBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        binding.textBindingInline.setOnClickListener { }
    }
}
