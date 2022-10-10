package com.githubyss.common.ui.app.page.recycler_view.multi

import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.common.ui.R


/**
 * ItemText
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/28 13:00:38
 */
class ItemText(var text: String) : BindingAdapterItem {
    override val layoutId: Int = R.layout.comui_item_text
}
