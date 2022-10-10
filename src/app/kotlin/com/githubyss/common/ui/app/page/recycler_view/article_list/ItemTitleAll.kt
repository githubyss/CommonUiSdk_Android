package com.githubyss.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.common.ui.R


/**
 * ItemTitleAll
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/05 16:22:16
 */
class ItemTitleAll(var id: String, var title: String, var isPlaying: Boolean, var isNew: Boolean, var datetime: String, isEditing: Boolean, checkState: String) : BindingAdapterItem {
    val isEditing by lazy { ObservableField<Boolean>() }
    val checkState by lazy { ObservableField<String>() }

    init {
        this.isEditing.set(isEditing)
        this.checkState.set(checkState)
    }

    override var layoutId: Int = R.layout.comui_item_article_list_title_all
}
