package com.githubyss.mobile.common.debug.recyclerview.fund.template.fundhotmanager

import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundHotManagerModel
 * 热门经理人数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:39
 */
data class FundHotManagerModel constructor(var title: String, var headerImageUrl: String, var bestReturn: String, var riseFallRatio: String, var description: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
