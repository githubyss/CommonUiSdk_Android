package com.githubyss.mobile.common.debug.recyclerview.fund.fundhotmanager

import com.githubyss.mobile.common.ui.recyclerview.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundHotManagerModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:39
 */
data class FundHotManagerModel constructor(var title: String, var headerImageUrl: String, var bestReturn: String, var riseFallRatio: String, var description: String, var jumpUrl: String, override var header: String, override var footer: String, @ItemType override var type: Int) : BaseItemModel(header, footer, type)
