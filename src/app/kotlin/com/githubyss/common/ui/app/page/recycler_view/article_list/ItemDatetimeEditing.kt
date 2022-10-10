package com.githubyss.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.common.ui.R


/**
 * ItemDatetimeEditing
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 11:11:40
 */
class ItemDatetimeEditing(var datetime: String, checkState: String) : BindingAdapterItem {
    val checkState by lazy { ObservableField<String>() }

    init {
        this.checkState.set(checkState)
    }

    override val layoutId: Int = R.layout.comui_item_article_list_datetime_editing
}
