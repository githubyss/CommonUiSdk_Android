package com.githubyss.common.ui.app.page.recycler_view.article_list

import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.common.ui.R


/**
 * ItemTitle
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/03 15:57:18
 */
open class ItemTitle(var id: String, var title: String, var isPlaying: Boolean, var isNew: Boolean) : BindingAdapterItem {
    override val layoutId: Int = R.layout.comui_item_article_list_title
}
