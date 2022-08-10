package com.githubyss.mobile.common.ui.app.page.recycler_view.multi

import androidx.annotation.DrawableRes
import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.mobile.common.ui.R


/**
 * ItemImage
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/28 12:34:25
 */
class ItemImage(@DrawableRes var picDrawableId: Int) : BindingAdapterItem {
    override val layoutId: Int = R.layout.comui_item_image
}
