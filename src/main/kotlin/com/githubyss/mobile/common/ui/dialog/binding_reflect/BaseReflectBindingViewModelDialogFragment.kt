package com.githubyss.mobile.common.ui.dialog.binding_reflect

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding


/**
 * BaseReflectBindingViewModelDialogFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/25 16:43:48
 */
abstract class BaseReflectBindingViewModelDialogFragment<B : ViewDataBinding> : RootReflectBindingDialogFragment<B>() {

    /** ****************************** Override ****************************** */

    /**  */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindLifecycleOwner()
        bindXmlData()
        observeViewModelData()
    }

    /**  */
    override fun onDestroy() {
        removeViewModelObserver()
        super.onDestroy()
    }


    /** ****************************** Abstract ****************************** */

    /** 绑定 Activity LifecycleOwner 到 ViewDataBinding */
    open fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    /** 绑定 ViewModel 到 ViewDataBinding */
    abstract fun bindXmlData()

    /** 观察 ViewModel 的数据变化 */
    abstract fun observeViewModelData()

    /** 移除 ViewModel 的数据观察 */
    abstract fun removeViewModelObserver()
}
