package com.githubyss.mobile.common.debug.recyclerview.search.template.appicon

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * AppIconModel
 * 应用图标数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:17:52
 */
data class AppIconModel constructor(var iconUrl: String, var label: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
