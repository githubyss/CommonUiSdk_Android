package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


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
