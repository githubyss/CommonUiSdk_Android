package com.githubyss.mobile.common.debug.recyclerview.fund.template.financeaq

import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FinanceAqModel
 * 财顾问答数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 11:55:16
 */
data class FinanceAqModel constructor(var title: String, var content: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
