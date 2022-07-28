package com.githubyss.mobile.common.ui.recycler_view.base.binding_inline

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * RootInlineBindingViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/08 09:59:43
 */
class RootInlineBindingViewHolder<B : ViewDataBinding>(binding: B) : RecyclerView.ViewHolder(binding.root) {
    lateinit var binding: B
}
