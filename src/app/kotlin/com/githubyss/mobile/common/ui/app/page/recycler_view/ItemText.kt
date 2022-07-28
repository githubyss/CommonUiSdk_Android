package com.githubyss.mobile.common.ui.app.page.recycler_view

import android.os.Parcel
import android.os.Parcelable
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


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
