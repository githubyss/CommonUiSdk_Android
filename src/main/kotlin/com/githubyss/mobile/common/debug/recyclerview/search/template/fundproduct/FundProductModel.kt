package com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct

import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundProductModel
 * 基金产品数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:32
 */
data class FundProductModel constructor(var title: String, var riseFallRatio: String, var code: String, var risk: String, var classify: String, var riseFallTimeSpan: String, var followCount: String, var isFollowed: Boolean, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
