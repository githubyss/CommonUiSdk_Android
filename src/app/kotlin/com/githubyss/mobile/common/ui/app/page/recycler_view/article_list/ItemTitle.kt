package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ItemTitle
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/03 15:57:18
 */
class ItemTitle(var id: String, var title: String, var isPlaying: Boolean, var isNew: Boolean) : BindingAdapterItem {
    override val layoutId: Int = R.layout.comui_item_article_list_title
}
