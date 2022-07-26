package com.githubyss.mobile.common.ui.app.page.custom_view

import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.bindView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentCustomViewBinding


/**
 * CustomViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/24 15:47:04
 */
class CustomViewFragment : BaseInlineBindingToolbarFragment<ComuiFragmentCustomViewBinding>(R.layout.comui_fragment_custom_view) {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = CustomViewFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val _binding by bindView<ComuiFragmentCustomViewBinding>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        binding = _binding
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle("")
    }
}
