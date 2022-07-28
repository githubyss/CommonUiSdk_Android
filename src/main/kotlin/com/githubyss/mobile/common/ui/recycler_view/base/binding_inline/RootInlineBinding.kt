package com.githubyss.mobile.common.ui.recycler_view.base.binding_inline

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.binding.binding_inflate.inflateBindingByViewGroup


/**
 * 通过 ViewGroup 获取 ViewHolder
 *
 * @param parent ViewGroup
 * @return ViewHolder 实例
 */
inline fun <reified B : ViewDataBinding> inflate(parent: ViewGroup): RootInlineBindingViewHolder<B> {
    return RootInlineBindingViewHolder(inflateBindingByViewGroup(parent))
}
