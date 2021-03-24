package com.githubyss.mobile.common.ui.recyclerview.layout

import android.view.View
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * LayoutModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 15:15:17
 */
data class LayoutModel constructor(var view: View?, override var header: String, override var footer: String, @ItemType override var type: Int) : BaseItemModel(header, footer, type)
