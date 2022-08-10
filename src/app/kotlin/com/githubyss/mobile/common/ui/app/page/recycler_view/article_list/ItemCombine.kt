package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.common.base.recycler_view.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.R


/**
 * ItemCombine
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 14:43:05
 */
open class ItemCombine(var datetime: String, var isFirst: Boolean, override var innerItems: ArrayList<ItemTitle>) : BindingAdapterDoubleLayerItem {
    override val layoutId: Int = R.layout.comui_item_article_list_combine
}
