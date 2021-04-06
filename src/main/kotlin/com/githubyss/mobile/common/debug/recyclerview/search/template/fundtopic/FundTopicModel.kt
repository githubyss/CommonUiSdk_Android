package com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FundTopicModel
 * 热门基金数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:19
 */
data class FundTopicModel constructor(var title: String, var riseFallRatio: String, var hint: String, var riseFallTimeSpan: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
