package com.githubyss.mobile.common.ui.base.view_binding.recycler_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.ui.base.view_binding.function.inflateBindingViewGroup


/**
 * BaseViewHolderBindingInline
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/08 09:59:43
 */
class BaseViewHolderBindingInline<B : ViewBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)

inline fun <reified B : ViewBinding> inflate(parent: ViewGroup): BaseViewHolderBindingInline<B> {
    return BaseViewHolderBindingInline(inflateBindingViewGroup(parent))
}
