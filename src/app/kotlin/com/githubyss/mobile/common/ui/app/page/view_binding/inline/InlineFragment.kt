package com.githubyss.mobile.common.ui.app.page.view_binding.inline

import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingFragment
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingInlineBinding


/**
 * InlineFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:44:22
 */
class InlineFragment : BaseInlineBindingFragment(R.layout.comui_fragment_view_binding_inline) {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = InlineFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComuiFragmentViewBindingInlineBinding>()
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        binding.textBindingInline.setOnClickListener { }
    }
}
