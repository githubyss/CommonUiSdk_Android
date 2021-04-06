package com.githubyss.mobile.common.debug.recyclerview.search.template.specialtopic

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * SpecialTopicIconModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 16:13:08
 */
data class SpecialTopicIconModel constructor(var iconUrl: String, var label: String, var jumpUrl: String, var jumpType: String, @ItemType override var type: Int) : BaseItemModel(type)
