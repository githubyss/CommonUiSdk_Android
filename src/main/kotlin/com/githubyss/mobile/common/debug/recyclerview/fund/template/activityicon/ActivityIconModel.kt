package com.githubyss.mobile.common.debug.recyclerview.fund.template.activityicon

import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * ActivityIconModel
 * 活动图标数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:29:46
 */
data class ActivityIconModel constructor(var iconUrl: String, var label: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
