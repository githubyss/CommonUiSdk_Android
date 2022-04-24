package com.githubyss.mobile.common.ui.app.page.custom_view

import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.bindView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentCustomViewBinding


/**
 * CustomViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/24 15:47:04
 */
class CustomViewFragment : BaseInlineBindingToolbarFragment(R.layout.comui_fragment_custom_view) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = CustomViewFragment::class.java.simpleName
    }

    private val binding by bindView<ComuiFragmentCustomViewBinding>()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
    }

    override fun setToolbarTitle() {
        setToolbarTitle("")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
