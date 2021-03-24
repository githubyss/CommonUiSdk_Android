package com.githubyss.mobile.common.debug.recyclerview.fund.goldproduct

import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * GoldProductModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 15:06:26
 */
data class GoldProductModel constructor(var title: String, var price: String, var unit: String, var code: String, var risk: String, var classify: String, var priceTime: String, var jumpUrl: String, override var header: String, override var footer: String, @ItemType override var type: Int) : BaseItemModel(header, footer, type)