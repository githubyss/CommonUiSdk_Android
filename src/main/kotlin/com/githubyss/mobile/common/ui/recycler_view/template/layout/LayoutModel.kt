package com.githubyss.mobile.common.ui.recycler_view.template.layout

import android.view.View
import com.githubyss.mobile.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONObject


/**
 * LayoutModel
 * 区块的数据类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 15:15:17
 */
data class LayoutModel constructor(var view: View?, @ItemType override var type: Int) : BaseItemModel(type) {
    override fun setProperties(json: JSONObject?) {
        TODO("Not yet implemented")
    }
}
