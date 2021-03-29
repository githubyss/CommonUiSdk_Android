package com.githubyss.mobile.common.ui.recyclerview.template.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * ListModel
 * 列表的数据类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/29 18:34:44
 */
data class ListModel constructor(var adapter: BaseItemAdapter, @RecyclerView.Orientation var orientation: Int, var context: Context, @ItemType override var type: Int) : BaseItemModel(type)
