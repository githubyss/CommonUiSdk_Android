package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.common.base.recycler_view.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.R


/**
 * ItemCombineEditing
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 16:20:27
 */
class ItemCombineEditing(var datetime: String, checkState: String, override var innerItems: ArrayList<ItemTitleEditing>) : BindingAdapterDoubleLayerItem {
    val checkState by lazy { ObservableField<String>() }

    init {
        this.checkState.set(checkState)
    }

    override val layoutId: Int = R.layout.comui_item_article_list_combine_editing
}
