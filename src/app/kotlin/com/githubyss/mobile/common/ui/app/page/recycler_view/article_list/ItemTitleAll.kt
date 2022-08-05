package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ItemTitleAll
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/05 16:22:16
 */
class ItemTitleAll(id: String, title: String, isPlaying: Boolean, isNew: Boolean, var datetime: String, isEditing: Boolean, checkState: String) : ItemTitle(id, title, isPlaying, isNew) {
    val isEditing by lazy { ObservableField<Boolean>() }
    val checkState by lazy { ObservableField<String>() }

    init {
        this.isEditing.set(isEditing)
        this.checkState.set(checkState)
    }

    override var layoutId: Int = R.layout.comui_item_article_list_title_all
}
