package com.githubyss.mobile.common.ui.recyclerview.template.list

import android.content.Context
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemAdapter
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import org.json.JSONObject


/**
 * ListModel
 * 一级列表的数据类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/29 18:34:44
 */
data class ListModel constructor(var adapter: BaseItemAdapter, var orientation: Int, var context: Context, var listener: BaseItemAdapter.OnItemClickListener? = null, @ItemType override var type: Int) : BaseItemModel(type) {
    override fun setProperties(json: JSONObject?) {
        TODO("Not yet implemented")
    }
}
