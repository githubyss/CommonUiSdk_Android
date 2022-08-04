package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ItemCombineEditing
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 16:20:27
 */
class ItemCombineEditing(var datetime: String, checkState: String, override val innerItems: List<ItemTitleEditing>) : BindingAdapterDoubleLayerItem {
    val checkState by lazy { ObservableField<String>() }

    init {
        this.checkState.set(checkState)
    }

    override val layoutId: Int = R.layout.comui_item_article_list_combine_editing
}
