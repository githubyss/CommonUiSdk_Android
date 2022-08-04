package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ItemTitleEditing
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 11:11:24
 */
class ItemTitleEditing(var id: String, var title: String, checkState: String) : BindingAdapterItem {
    val checkState by lazy { ObservableField<String>() }

    init {
        this.checkState.set(checkState)
    }

    override val layoutId: Int = R.layout.comui_item_article_list_title_editing
}
