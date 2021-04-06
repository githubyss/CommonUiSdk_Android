package com.githubyss.mobile.common.debug.recyclerview.search.template.faq

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * FaqModel
 * 常见问题数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:26:05
 */
data class FaqModel constructor(var content: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
