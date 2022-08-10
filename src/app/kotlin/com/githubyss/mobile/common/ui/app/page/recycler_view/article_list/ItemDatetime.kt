package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.mobile.common.ui.R


/**
 * ItemDatetime
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/03 15:47:28
 */
class ItemDatetime(var datetime: String, var isFirst: Boolean) : BindingAdapterItem {
    override val layoutId: Int = R.layout.comui_item_article_list_datetime
}
