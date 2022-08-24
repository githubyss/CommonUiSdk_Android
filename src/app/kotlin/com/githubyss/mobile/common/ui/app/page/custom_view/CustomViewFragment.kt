package com.githubyss.mobile.common.ui.app.page.custom_view

import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.ui.databinding.ComuiFragmentCustomViewBinding


/**
 * CustomViewFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/24 15:47:04
 */
class CustomViewFragment : BaseReflectBindingToolbarFragment<ComuiFragmentCustomViewBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = CustomViewFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle("")
    }
}
