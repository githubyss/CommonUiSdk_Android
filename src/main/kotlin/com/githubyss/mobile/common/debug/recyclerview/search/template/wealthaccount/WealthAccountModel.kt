package com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * WealthAccountModel
 * 财富号数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:04:58
 */
data class WealthAccountModel constructor(var imageUrl: String, var title: String, var content: String, var isFollowed: Boolean, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
