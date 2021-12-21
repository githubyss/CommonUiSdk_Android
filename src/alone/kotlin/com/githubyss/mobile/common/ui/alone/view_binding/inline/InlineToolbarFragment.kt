package com.githubyss.mobile.common.ui.alone.view_binding.inline

import android.view.View
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarFragment
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentViewBindingInlineBinding


/**
 * InlineToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:52:55
 */
class InlineToolbarFragment : BaseToolbarFragment(R.layout.comui_fragment_view_binding_inline) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG: String = InlineToolbarFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComuiFragmentViewBindingInlineBinding>()
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun init() {
        initView()
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comui_title_view_binding_inline)
        // setToolbarTitle("Inline Toolbar Fragment")
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun initView() {
        binding.textBindingInline.setOnClickListener(onClickListener)
    }
    
    
    /** ********** ********** ********** Implementations ********** ********** ********** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
        }
    }
}
