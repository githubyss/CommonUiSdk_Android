package com.githubyss.mobile.common.debug.recyclerview.fund.fundhot

import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundHotModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:19
 */
data class FundHotModel constructor(var title: String, var riseFallRatio: String, var hint: String, var riseFallTimeSpan: String, var jumpUrl: String, override var header: String, override var footer: String, @ItemType override var type: Int) : BaseItemModel(header, footer, type)
