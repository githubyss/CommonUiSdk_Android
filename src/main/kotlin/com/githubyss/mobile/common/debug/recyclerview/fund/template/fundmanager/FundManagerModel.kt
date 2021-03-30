package com.githubyss.mobile.common.debug.recyclerview.fund.template.fundmanager

import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundManagerModel
 * 热门经理人数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:39
 */
data class FundManagerModel constructor(var imageUrl: String, var title: String, var bestReturn: String, var riseFallRatio: String, var description: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
