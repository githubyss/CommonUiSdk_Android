package com.githubyss.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.common.base.recycler_view.binding.BindingAdapterDoubleLayerItem
import com.githubyss.common.ui.R


/**
 * ItemCombineAll
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/05 16:22:10
 */
class ItemCombineAll(var datetime: String, var isFirst: Boolean, override var innerItems: ArrayList<ItemTitleAll>, isEditing: Boolean, checkState: String) : BindingAdapterDoubleLayerItem {
    val isEditing by lazy { ObservableField<Boolean>() }
    val checkState by lazy { ObservableField<String>() }

    init {
        this.isEditing.set(isEditing)
        this.checkState.set(checkState)
    }

    override val layoutId: Int = R.layout.comui_item_article_list_combine_all
}
