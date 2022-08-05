package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import androidx.databinding.ObservableField
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ItemCombine
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 14:43:05
 */
open class ItemCombine(var position: Int, var datetime: String, override var innerItems: List<ItemTitle>) : BindingAdapterDoubleLayerItem {
    override val layoutId: Int = R.layout.comui_item_article_list_combine
}
