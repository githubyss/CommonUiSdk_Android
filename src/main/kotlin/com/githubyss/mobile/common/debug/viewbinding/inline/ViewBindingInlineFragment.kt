package com.githubyss.mobile.common.debug.viewbinding.inline

import android.os.Bundle
import android.view.View
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.viewbinding.page.inline.BaseToolbarFragmentBindingInline
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingInlineBinding
import com.githubyss.mobile.common.ui.kotlininline.bindView


/**
 * ViewBindingInlineFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:52:55
 */
class ViewBindingInlineFragment : BaseToolbarFragmentBindingInline(R.layout.comui_fragment_view_binding_inline) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        private val TAG = ViewBindingInlineFragment::class.simpleName ?: "simpleName is null"
    }
    
    private val binding by bindView<ComuiFragmentViewBindingInlineBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        setToolbarTitle(R.string.comui_view_binding_inline_title)
        
        binding.tvBindingInline.setOnClickListener(onClickListener)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** **********  */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
        }
    }
}
